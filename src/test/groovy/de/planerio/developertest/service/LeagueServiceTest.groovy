package de.planerio.developertest.service

import de.planerio.developertest.UnitTest
import de.planerio.developertest.exception.LeagueNotFoundException
import de.planerio.developertest.exception.ResourceExistsException
import de.planerio.developertest.model.Country
import de.planerio.developertest.model.CountryRequest
import de.planerio.developertest.model.League
import de.planerio.developertest.model.LeagueRequest
import de.planerio.developertest.model.LeagueUpdateRequest
import de.planerio.developertest.repository.CountryRepository
import de.planerio.developertest.repository.LeagueRepository
import org.junit.experimental.categories.Category
import org.springframework.dao.EmptyResultDataAccessException
import spock.lang.Specification

@Category(UnitTest.class)
class LeagueServiceTest extends Specification {

    def leagueRepository = Mock(LeagueRepository)
    def countryRepository = Mock(CountryRepository)
    def leagueService = new LeagueService(leagueRepository, countryRepository)
    def country = new Country(id: 1223, name: "German", language: "de")
    def league = new League(id: 123, name: "My league", country: country)
    def countryRequest = new CountryRequest(name: "German", language: "de")
    def leagueRequest = new LeagueRequest(name: "My league", country: countryRequest)
    def leagueUpdateRequest = new LeagueUpdateRequest(name: "My league", country: countryRequest)

    def "save - country not exists - create other country"(){
        when:
        def leagueResponse = leagueService.save(leagueRequest)

        then:
        leagueResponse.name == leagueRequest.name
        leagueResponse.country.name == leagueRequest.country.name
        leagueResponse.country.language == leagueRequest.country.language

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()

        and:
        1 * countryRepository.findCountryByNameAndLanguage(_,_) >> Optional.empty()

        and:
        1 * leagueRepository.save(_) >> league
    }

    def "save - country exists - don't create another country"(){
        given:
        def country = new Country(id: 123, name: "United Kingdom", language: "en-gb")
        def newCountryRequest = new CountryRequest(name: "United Kingdom", language: "en-gb")
        def newLeagueRequest = new LeagueRequest(name: "My league", country: newCountryRequest)

        when:
        def leagueResponse = leagueService.save(newLeagueRequest)

        then:
        leagueResponse.name == leagueRequest.name
        leagueResponse.country.name == leagueRequest.country.name
        leagueResponse.country.language == leagueRequest.country.language

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()

        and:
        1 * countryRepository.findCountryByNameAndLanguage(_,_) >> Optional.of(country)

        and:
        1 * leagueRepository.save(_) >> league
    }

    def "save - there is a league registered - exception is thrown"(){
        when:
        leagueService.save(leagueRequest)

        then:
        thrown(ResourceExistsException)

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.of(league)
    }

    def "find"(){
        when:
        def leagueResponse = leagueService.find(1)

        then:
        leagueResponse.name == "My league"
        leagueResponse.country.name == "German"
        leagueResponse.country.language == "de"

        and:
        1 * leagueRepository.findById(_) >> Optional.of(league)
    }

    def "find - league not found - exception is thrown"(){
        when:
        leagueService.find(1)

        then:
        thrown(LeagueNotFoundException)

        and:
        1 * leagueRepository.findById(_) >> Optional.empty()
    }

    def "findAll"(){
        when:
        def leaguesResponse = leagueService.findAll()

        then:
        leaguesResponse.size() > 9

        and:
        1 * leagueRepository.findAll() >> createLeague(10)
    }

    def "findAll - leagues not found - exception is thrown"(){
        when:
        leagueService.findAll()

        then:
        thrown(LeagueNotFoundException)

        and:
        1 * leagueRepository.findAll() >> []
    }

    def "update"(){
        when:
        leagueService.update(leagueUpdateRequest, 1)

        then:
        noExceptionThrown()

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()

        and:
        1 * leagueRepository.findById(_) >> Optional.of(league)
    }

    def "update - there is a league registered - exception is thrown"(){
        when:
        leagueService.update(leagueUpdateRequest, 1)

        then:
        thrown(ResourceExistsException)

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.of(league)
    }

    def "update - leagues not found - exception is thrown"(){
        when:
        leagueService.update(leagueUpdateRequest, 1)

        then:
        thrown(LeagueNotFoundException)

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()

        and:
        1 * leagueRepository.findById(_) >> Optional.empty()
    }

    def "delete"(){
        when:
        leagueService.delete(123)

        then:
        noExceptionThrown()
    }

    def "delete - leagues not found - exception is thrown"(){
        when:
        leagueService.delete(123)

        then:
        thrown(LeagueNotFoundException)

        and:
        leagueRepository.deleteById(123) >> {throw new EmptyResultDataAccessException("",1)}
    }

    def createLeague(numberOfLeagues){
        int count = 1
        def leagues = []
        while(count<=numberOfLeagues) {
            leagues.add(new League(id: 123, name: "My league " + count, country: new Country(id: 123, name: "German", language: "de")))
            count++
        }
        return leagues
    }
}
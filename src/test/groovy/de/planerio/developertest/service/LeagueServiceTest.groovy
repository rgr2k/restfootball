package de.planerio.developertest.service

import de.planerio.developertest.UnitTest
import de.planerio.developertest.exception.LeagueNotFoundException
import de.planerio.developertest.exception.ResourceExistsException
import de.planerio.developertest.model.Country
import de.planerio.developertest.model.CountryRequest
import de.planerio.developertest.model.League
import de.planerio.developertest.model.LeagueRequest
import de.planerio.developertest.model.LeagueUpdateRequest
import de.planerio.developertest.repository.LeagueRepository
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class LeagueServiceTest extends Specification {

    def leagueRepository = Mock(LeagueRepository)
    def leagueService = new LeagueService(leagueRepository)
    def country = new Country(name: "German", language: "de")
    def league = new League(name: "My league", country: country)
    def countryRequest = new CountryRequest(name: "German", language: "de")
    def leagueRequest = new LeagueRequest(name: "My league", country: countryRequest)
    def leagueUpdateRequest = new LeagueUpdateRequest(name: "My league", country: countryRequest)
    def leagues = [
            new League(name: "My league 1", country: new Country(name: "German", language: "de")),
            new League(name: "My league 1", country: new Country(name: "Brazil", language: "pt")),
            new League(name: "My league 1", country: new Country(name: "Canada", language: "en")),
            new League(name: "My league 1", country: new Country(name: "Greece", language: "el"))
    ]

    def "save"(){
        when:
        def leagueResponse = leagueService.save(leagueRequest)

        then:
        leagueResponse.name == leagueRequest.name
        leagueResponse.country.name == leagueRequest.country.name
        leagueResponse.country.language == leagueRequest.country.language

        and:
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()

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
        leaguesResponse.size() == 4

        and:
        1 * leagueRepository.findAll() >> leagues
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
        1 * leagueRepository.findLeagueByCountryName(_) >> Optional.empty()
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
        leagueRepository.findById(_) >> Optional.of(league)
    }

    def "delete - leagues not found - exception is thrown"(){
        when:
        leagueService.delete(123)

        then:
        thrown(LeagueNotFoundException)

        and:
        1 * leagueRepository.findById(_) >> Optional.empty()
    }
}

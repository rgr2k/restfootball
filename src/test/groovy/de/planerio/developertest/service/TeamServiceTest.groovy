package de.planerio.developertest.service

import de.planerio.developertest.UnitTest
import de.planerio.developertest.exception.LeagueNotFoundException
import de.planerio.developertest.exception.ResourceNotAcceptableException
import de.planerio.developertest.exception.TeamNotFoundException
import de.planerio.developertest.model.Country
import de.planerio.developertest.model.League
import de.planerio.developertest.model.Team
import de.planerio.developertest.model.TeamRequest
import de.planerio.developertest.model.TeamUpdateRequest
import de.planerio.developertest.repository.LeagueRepository
import de.planerio.developertest.repository.TeamRepository
import org.junit.experimental.categories.Category
import org.springframework.dao.EmptyResultDataAccessException
import spock.lang.Specification

@Category(UnitTest.class)
class TeamServiceTest extends Specification {

    def teamRepository = Mock(TeamRepository)
    def leagueRepository = Mock(LeagueRepository)
    def teamService = new TeamService(teamRepository, leagueRepository)
    def teamRequest = new TeamRequest(name: "My Team", leagueId: 1)
    def league = new League(id: 123, name: "My League", country: new Country(name: "German", language: "de"))
    def team = new Team(name: "My Team", league: league, players: [])

    def "save - there are more than 19 teams on league - exception is thrown"(){
        given:
        league.teams = [
                new Team(name: "Team 1"), new Team(name: "Team 2"),
                new Team(name: "Team 3"), new Team(name: "Team 4"),
                new Team(name: "Team 5"), new Team(name: "Team 6"),
                new Team(name: "Team 7"), new Team(name: "Team 8"),
                new Team(name: "Team 9"), new Team(name: "Team 10"),
                new Team(name: "Team 11"), new Team(name: "Team 12"),
                new Team(name: "Team 13"), new Team(name: "Team 14"),
                new Team(name: "Team 15"), new Team(name: "Team 16"),
                new Team(name: "Team 17"), new Team(name: "Team 18"),
                new Team(name: "Team 19"), new Team(name: "Team 20"),
        ]

        when:
        teamService.save(teamRequest)

        then:
        thrown(ResourceNotAcceptableException)

        and:
        1 * leagueRepository.findById(_) >> Optional.of(league)
    }

    def "save - league not found - exception is thrown"(){
        when:
        teamService.save(teamRequest)

        then:
        thrown(LeagueNotFoundException)

        and:
        1 * leagueRepository.findById(_) >> Optional.empty()
    }

    def "save"(){
        given:
        league.teams = [new Team(name: "Team 1"), new Team(name: "Team 2")]

        when:
        def teamResponse = teamService.save(teamRequest)

        then:
        teamResponse.name == "My Team"

        and:
        1 * leagueRepository.findById(_) >> Optional.of(league)

        and:
        1 * teamRepository.save(_) >> new Team(id: 123, name: "My Team", league: league, players: [])
    }

    def "findById"(){
        when:
        def teamResponse = teamService.findById(1)

        then:
        teamResponse.name == teamResponse.name

        and:
        1 * teamRepository.findById(_) >> Optional.of(team)
    }

    def "findById - team not found - exception is thrown"(){
        when:
        teamService.findById(1)

        then:
        thrown(TeamNotFoundException)

        and:
        1 * teamRepository.findById(_) >> Optional.empty()
    }

    def "findAll"(){
        when:
        def teams = teamService.findAll()

        then:
        teams.size() == 1

        and:
        1 * teamRepository.findAll() >> [team]
    }

    def "findAll - teams not found - exception is thrown"(){
        when:
        teamService.findAll()

        then:
        thrown(TeamNotFoundException)

        and:
        1 * teamRepository.findAll() >> []
    }

    def "delete"(){
        when:
        teamService.delete(1)

        then:
        teamRepository.deleteById(_)
    }

    def "delete - team not found - exception is thrown"(){
        when:
        teamService.delete(1)

        then:
        thrown(TeamNotFoundException)

        and:
        1 * teamRepository.deleteById(_) >> {throw new EmptyResultDataAccessException("",1)}
    }

    def "update"(){
        given:
        def teamUpdateRequest = new TeamUpdateRequest(name: "My other name")

        when:
        teamService.update(teamUpdateRequest, 1)

        then:
        1 * teamRepository.findById(_) >> Optional.of(team)
        1 * teamRepository.save(_)
    }

    def "update - team not found - exception is thrown"(){
        given:
        def teamUpdateRequest = new TeamUpdateRequest(name: "My other name")

        when:
        teamService.update(teamUpdateRequest, 1)

        then:
        thrown(TeamNotFoundException)

        and:
        1 * teamRepository.findById(_) >> Optional.empty()
    }

}
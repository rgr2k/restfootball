package de.planerio.developertest


import com.jayway.restassured.http.ContentType
import de.planerio.developertest.controller.country.CountryController
import de.planerio.developertest.controller.country.CountryResource
import de.planerio.developertest.controller.league.LeagueResource
import de.planerio.developertest.controller.player.PlayerResource
import de.planerio.developertest.controller.team.TeamResource
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.*

/**
 * This is interface identifies all Groovy specification classes which contain to integration tests.
 */
@SpringBootTest(
        classes = [DeveloperTestApplication],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ControllerTest extends Specification {

    @LocalServerPort
    int port

    @SpringBean
    CountryController countryController = Mock()

    def creatRequest(){
        given().log().all().port(port).accept(ContentType.JSON).contentType(ContentType.JSON)
    }

}
package de.planerio.developertest.service

import de.planerio.developertest.UnitTest
import de.planerio.developertest.exception.CountryNotFoundException
import de.planerio.developertest.model.Country
import de.planerio.developertest.model.CountryRequest
import de.planerio.developertest.model.CountryUpdateRequest
import de.planerio.developertest.repository.CountryRepository
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class CountryServiceTest extends Specification {

    def countryRepository = Mock(CountryRepository)
    def countryService = new CountryService(countryRepository)
    def country = new Country(name: "German", language: "de")
    def countryRequest = new CountryRequest(name: "German", language: "de")

   def "save"(){
       when:
       def countryResponse = countryService.save(countryRequest)

       then:
       countryResponse.name == countryRequest.name
       countryResponse.language == countryRequest.language

       and:
       1 * countryRepository.save(_) >> country
   }

   def "find"(){
       when:
       def countryResponse = countryService.find(1234)

       then:
       countryResponse.name == "German"
       countryResponse.language == "de"

       and:
       1 * countryRepository.findById(_) >> Optional.of(country)
   }

   def "find - country not found - exception is thrown"(){
       when:
       countryService.find(1234)

       then:
       thrown(CountryNotFoundException)

       and:
       1 * countryRepository.findById(_) >> Optional.empty()
   }

   def "findAll"(){
       when:
       def countries = countryService.findAll()

       then:
       countries.size() == 1

       and:
       1 * countryRepository.findAll() >> [country]
   }

   def "findAll - countries not found - exception is thrown"(){
        when:
        countryService.findAll()

        then:
        thrown(CountryNotFoundException)

        and:
        1 * countryRepository.findAll() >> []
   }

   def "update"(){
       when:
       countryService.update(new CountryUpdateRequest(name: "German", language: "de"), 123)

       then:
       countryRepository.findById(_) >> Optional.of(country)
   }

   def "update - countries not found - exception is thrown"(){
       when:
       countryService.update(new CountryUpdateRequest(name: "German", language: "de"), 123)

       then:
       thrown(CountryNotFoundException)

       and:
       1 * countryRepository.findById(_) >> Optional.empty()
   }

   def "delete"(){
       when:
       countryService.delete(123)

       then:
       countryRepository.findById(_) >> Optional.of(country)
   }

   def "delete - countries not found - exception is thrown"(){
       when:
       countryService.delete(123)

       then:
       thrown(CountryNotFoundException)

       and:
       1 * countryRepository.findById(_) >> Optional.empty()
   }

   def "findCountryByNameAndLanguage"(){
       when:
       def country = countryService.findCountryByNameAndLanguage(_ as String, _ as String)

       then:
       country.isPresent()
       country.get().name == "German"
       country.get().language == "de"

       and:
       1 * countryRepository.findCountryByNameAndLanguage(_,_) >> Optional.of(country)
   }

   def "findCountryByNameAndLanguage - countries not found - empty country is returned "(){
        when:
        def country = countryService.findCountryByNameAndLanguage(_ as String, _ as String)

        then:
        !country.isPresent()

        and:
        1 * countryRepository.findCountryByNameAndLanguage(_,_) >> Optional.empty()
   }

}

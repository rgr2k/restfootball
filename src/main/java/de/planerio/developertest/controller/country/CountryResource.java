package de.planerio.developertest.controller.country;

import de.planerio.developertest.model.Country;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@Api(value = "countries")
@RequestMapping(value = "/v1/countries")
public interface CountryResource {

    @ApiOperation(value = "Get countries")
    @GetMapping
    Iterable<Country> getCountries();

    @ApiOperation(value = "Delete country")
    @DeleteMapping("/{countryId}")
    void deleteCountry(@ApiParam(value = "ID of the country that needs to be deleted",required=true) @PathVariable long countryId);

    @ApiOperation(value = "Update country")
    @PutMapping("/{countryId}")
    void updateCountry(@ApiParam(value = "Updated country object" ,required=true) @RequestBody Country updatedCountry, @ApiParam(value = "ID of the country that needs to be updated",required=true) @PathVariable long countryId);

    // @PostMapping("/country/update/{countryId}")
    // public void deleteCountry(@RequestBody Country updatedCountry, @PathVariable long countryId) {
    //     cRepo.findById(countryId).orElseThrow();
    //     cRepo.save(updatedCountry);
    // }

    // @PostMapping("/country/update/{countryId}")
    // public void deleteCountry(@RequestBody Country updatedCountry, @PathVariable long countryId) {
    //     cRepo.findById(countryId).orElseThrow();
    //     cRepo.save(updatedCountry);
    // }
}

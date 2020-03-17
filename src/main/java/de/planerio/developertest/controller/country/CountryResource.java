package de.planerio.developertest.controller.country;

import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.model.CountryResponse;
import de.planerio.developertest.model.CountryUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "countries")
@RequestMapping(value = "/v1/countries")
public interface CountryResource {

    @ApiOperation(value = "Get countries")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<CountryResponse> retrieveAllCountries();

    @ApiOperation(value = "Get country by id")
    @GetMapping(value = "/{countryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    CountryResponse retrieveCountry(@ApiParam(value = "ID of the country that needs to be found", required=true, example = "1") @PathVariable long countryId);

    @ApiOperation(value = "Create country")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CountryResponse createCountry(@ApiParam(value = "Created country object" , required=true) @Valid @RequestBody CountryRequest countryRequest);

    @ApiOperation(value = "Delete country")
    @DeleteMapping("/{countryId}")
    void deleteCountry(@ApiParam(value = "ID of the country that needs to be deleted", required=true, example = "1") @PathVariable long countryId);

    @ApiOperation(value = "Update country")
    @PutMapping(value = "/{countryId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateCountry(@ApiParam(value = "Updated country object" ,required=true) @RequestBody CountryUpdateRequest countryUpdate, @ApiParam(value = "ID of the country that needs to be updated", required=true, example = "1") @PathVariable long countryId);
}

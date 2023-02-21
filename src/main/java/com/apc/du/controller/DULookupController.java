package com.apc.du.controller;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.commons.dto.BarangayDTO;
import com.apc.du.commons.dto.CityDTO;
import com.apc.du.commons.dto.PostalCodeDTO;
import com.apc.du.commons.dto.ProvinceDTO;
import com.apc.du.config.SwaggerConfiguration;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.services.DULookupService;
import com.apc.du.services.helpers.ResponseHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH)
@Api(tags = {SwaggerConfiguration.DU})
@Slf4j
public class DULookupController {

    @Autowired
    private DULookupService duLookupService;

    @ApiIgnore
    @GetMapping("/du")
    public BaseResponse getDistributionUtility(
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String barangay,
            @RequestParam String postalCode
    ) throws ServiceDisconnectedException {
        return duLookupService.getDistributionUtility(province, city, barangay, postalCode);
    }

    @ApiOperation(value = "Creates/Updates postal code", notes = "Returns the created/updated postal code")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 503, message = "Service Disconnected")
    })
    @PostMapping(value = "/postalCode", produces = {"application/json"})
    public ResponseEntity<BaseResponse> postalCode(@RequestBody @Valid PostalCodeDTO postalCode) throws APException, IllegalAccessException {
        BaseResponse result;
        if (postalCode.getId() != null) {
            result = duLookupService.postalCode(postalCode, postalCode.getId());
        } else {
            result = duLookupService.postalCode(postalCode);
        }

        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }

    @ApiOperation(value = "Creates/Updates province", notes = "Returns the created/updated province")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 503, message = "Service Disconnected")
    })
    @PostMapping(value = "/province", produces = {"application/json"})
    public ResponseEntity<BaseResponse> province(@RequestBody @Valid ProvinceDTO province) throws APException, IllegalAccessException {
        BaseResponse result;
        if (province.getId() != null) {
            result = duLookupService.province(province, province.getId());
        } else {
            result = duLookupService.province(province);
        }

        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }

    @ApiOperation(value = "Creates/Updates city", notes = "Returns the created/updated city")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 503, message = "Service Disconnected")
    })
    @PostMapping(value = "/city", produces = {"application/json"})
    public ResponseEntity<BaseResponse> city(@RequestBody @Valid CityDTO city) throws APException, IllegalAccessException {
        BaseResponse result;
        if (city.getId() != null) {
            result = duLookupService.city(city, city.getId());
        } else {
            result = duLookupService.city(city);
        }

        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }

    @ApiOperation(value = "Creates/Updates barangay", notes = "Returns the created/updated barangay")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 503, message = "Service Disconnected")
    })
    @PostMapping(value = "/barangay", produces = {"application/json"})
    public ResponseEntity<BaseResponse> barangay(@RequestBody @Valid BarangayDTO barangay) throws APException, IllegalAccessException {
        BaseResponse result;
        if (barangay.getId() != null) {
            result = duLookupService.barangay(barangay, barangay.getId());
        } else {
            result = duLookupService.barangay(barangay);
        }

        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }

    @ApiOperation(value = "Get All Locations with custom filter", notes = "Returns list of Distribution Utility location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "DU Location Not Found")})
    @GetMapping(value = "/locations", produces = {"application/json"})
    public ResponseEntity<BaseResponse> getAll
            (@RequestParam(value = "DU", required = false) @ApiParam(value = "Filter Distribution Utility (VECO, DLPC)") String du,
             @RequestParam(value = "province", required = false) @ApiParam(value = "Filter Province") String province,
             @RequestParam(value = "city", required = false) @ApiParam(value = "Filter City") String city,
             @RequestParam(value = "postalCode", required = false) @ApiParam(value = "Filter Postal Code") String postalCode,
             @RequestParam(value = "barangay", required = false) @ApiParam(value = "Filter Barangay") String barangay) throws APException {

        BaseResponse result = duLookupService.findAll(du, province, city, postalCode, barangay);
        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }
}

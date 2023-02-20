package com.apc.du.controller;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.commons.dto.LocationsDTO;
import com.apc.du.config.SwaggerConfiguration;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.services.DULookupService;
import com.apc.du.services.helpers.ResponseHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH)
@Api(tags = {SwaggerConfiguration.DU})
@Slf4j
public class DULookupController {

    @Autowired
    private DULookupService dULookupService;


    @GetMapping("/du")
    public BaseResponse getDistributionUtility(
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String barangay,
            @RequestParam String postalCode
    ) throws ServiceDisconnectedException {
        return dULookupService.getDistributionUtility(province, city, barangay, postalCode);
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

        BaseResponse result = dULookupService.findAll(du, province, city, postalCode, barangay);
        return new ResponseEntity<>(result, ResponseHelper.getHttpStatus(result.getStatusCode()));
    }
}

package com.apc.du.controller;


import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.config.SwaggerConfiguration;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.services.impl.DULookupService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH )
@Api(tags = {SwaggerConfiguration.DU})
@Slf4j
public class DULookupController {

    @Autowired
    private DULookupService dULookupService;

    @GetMapping("/du")
    public BaseResponse getDistributionUtility(@RequestParam String province,
                              @RequestParam String city,
                              @RequestParam String barangay,
                              @RequestParam(required = false) String barangayCode) throws ServiceDisconnectedException {

        return dULookupService.getDistributionUtility(province, city, barangay, barangayCode);
    }

}

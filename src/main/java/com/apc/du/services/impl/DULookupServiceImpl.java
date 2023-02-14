package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.LocationsDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.services.DULookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class DULookupServiceImpl implements DULookupService {

    @Autowired
    private BarangayRepository barangayRepository;

    @Override
    public BaseResponse getDistributionUtility(String province, String city, String barangay, String postalCode) throws ServiceDisconnectedException {
        List<APIResponseDTO> duList = new ArrayList<>();
        try {
            city = city.trim();
            duList = barangayRepository.getDUByProvinceCityBarangayPostalCode(province, city, barangay, postalCode);
            log.info("========== All DU fetched successfully. Size : {}", duList);

            if (duList.isEmpty()) {
                return errorResponse();
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching all DU. Message: {}", e.getMessage());
            throw new ServiceDisconnectedException(APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.REQUEST_TIMEOUT);
        }

        return new BaseResponse(duList);
    }

    @Override
    public BaseResponse findAll(String du, String province, String city, String postalCode, String barangay) throws APException {

        log.info(" DU: {}, Provice: {}, City: {}, PostalCode: {}, Barangay: {}", du, province, city, postalCode, barangay);

        List<LocationsDTO> locations;

        du = formatStr(du);
        province = formatStr(province);
        city = formatStr(city);
        postalCode = formatStr(postalCode);
        barangay = formatStr(barangay);

        try {
            locations = barangayRepository.getAllDULocations(du, province, city, postalCode, barangay);
            if (locations.isEmpty()) {
                return new BaseResponse<>(new HashMap<>())
                        .setStatusCodeMessage(
                                String.valueOf(APIResponse.DU_LOCATION_NOT_FOUND.getCode()),
                                APIResponse.DU_LOCATION_NOT_FOUND.getDescription());
            }
            log.info("DU List fetched successfully...");
        } catch (Exception e) {
            log.error("Exception occurred while fetching DU. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new BaseResponse<>(locations).setStatusCodeMessage(String.valueOf(HttpStatus.OK.value()), "Success");
    }

    /**
     * Return formatted string
     * > To lowercase
     * > Remove whitespace
     *
     * @param s
     * @return
     */
    private String formatStr(String s) {
        String result = "";
        if (!StringUtils.isEmpty(s)) {
            result = s.toLowerCase();
            result = result.replace(" ", "");
        }
        return result;
    }

    private BaseResponse<APIErrorResponseDTO> errorResponse() {
        BaseResponse<APIErrorResponseDTO> err = new BaseResponse<>();

        err.setStatusCode(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        err.setMessage("Resource Not Found");
        err.setData(APIErrorResponseDTO.builder().error("No Distribution Utility found for the location provided").build());
        return err;
    }
}

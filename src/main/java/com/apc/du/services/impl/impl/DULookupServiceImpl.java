package com.apc.du.services.impl.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.services.impl.DULookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DULookupServiceImpl implements DULookupService {

    @Autowired
    private BarangayRepository barangayRepository;

    @Override
    public BaseResponse getDistributionUtility(String province, String city, String barangay, String barangayCode) throws ServiceDisconnectedException {
        List<DistributionUtilityDTO> duList = null;
        try {
            if (StringUtils.isNotEmpty(barangayCode)) {
                log.info("========= Fetching by barangayCode");
                duList = barangayRepository.getDUByBarangayCode(barangayCode);
            } else {
                log.info("========= Fetching by provinceCityBarangay");
                city = cityChecker(city.trim());
                duList = barangayRepository.getDUByProvinceCityBarangay(province, city, barangay);
            }
            log.info("========== All DU fetched successfully. Size : {}", duList);

            if (duList.size() == 0) {
                return errorResponse();
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching all DU. Message: {}", e.getMessage());
            throw new ServiceDisconnectedException(APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.REQUEST_TIMEOUT);
        }

        return new BaseResponse(duList);
    }

    private BaseResponse<APIErrorResponseDTO> errorResponse() {
        BaseResponse<APIErrorResponseDTO> err = new BaseResponse<>();

        err.setStatusCode(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        err.setMessage("Resource Not Found");
        err.setData(APIErrorResponseDTO.builder().error("No Distribution Utility for the location provided").build());
        return err;
    }

    private String cityChecker(String city) {
        log.info("========= START: city check");
        if ((city.contains("city")) && (city.contains("City"))) {
            log.info("=========: city check");
            city = city.split(" ")[0];
        }

        return city.trim();
    }
}

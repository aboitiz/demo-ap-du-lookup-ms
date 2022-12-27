package com.apc.du.services.impl.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.commons.dto.DUDTO;
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
    public BaseResponse getDUByCityBarangay(String province, String city, String barangay, String barangayCode) throws ServiceDisconnectedException {
        List<DUDTO> duList = null;
        try {
            if (StringUtils.isNotEmpty(barangayCode)) {
                log.info("========= Fetching du by barangayCode");
                duList = barangayRepository.getDUByBarangayCode(barangayCode);
            } else {
                log.info("========= Fetching du by provinceCityBarangay");
                city = cityChecker(city.trim());
                duList = barangayRepository.getDUByProvinceCityBarangay(province, city, barangay);
            }
            log.info("========== All du fetched successfully. Size : {}", duList);
        } catch (Exception e) {
            log.error("Exception occurred while fetching all du. Message: {}", e.getMessage());
            throw new ServiceDisconnectedException("408", HttpStatus.REQUEST_TIMEOUT);
        }

        return new BaseResponse(duList);
    }

    private BaseResponse<APIErrorResponseDTO> buildServiceDisconnectedResponse() {
        BaseResponse<APIErrorResponseDTO> err = new BaseResponse<>();

        err.setStatusCode(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()));
        err.setMessage(APIResponse.SERVICE_DISCONNECTED.getMessage());
        err.setData(APIErrorResponseDTO.builder().error(APIResponse.SERVICE_DISCONNECTED.getDescription()).build());
        return err;
    }

    private String cityChecker(String city){
        log.info("========= START: city check");
        if ((city.contains("city")) && (city.contains("City"))) {
            log.info("=========: city check");
            city = city.split(" ")[0];
        }

        return city.trim();
    }
}

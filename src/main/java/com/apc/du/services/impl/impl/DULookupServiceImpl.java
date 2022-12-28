package com.apc.du.services.impl.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.model.City;
import com.apc.du.model.DistributionUtility;
import com.apc.du.model.PostalCode;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.repository.CityRepository;
import com.apc.du.repository.DistributionUtilityRepository;
import com.apc.du.repository.PostalCodeRepository;
import com.apc.du.services.impl.DULookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DULookupServiceImpl implements DULookupService {

    @Autowired
    private BarangayRepository barangayRepository;

    @Autowired
    private DistributionUtilityRepository distributionUtilityRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public BaseResponse getDistributionUtility(String province, String city, String barangay, String postalCode) throws ServiceDisconnectedException {
        List<APIResponseDTO> duList = new ArrayList<>();
        try {
            if (StringUtils.isNotEmpty(postalCode)) {
                log.info("========= Fetching by postalCode");
                List<Map> datas;
                datas = barangayRepository.getDUByPostalCode(postalCode);
                for (Map data : datas) {
                    APIResponseDTO dto = new APIResponseDTO(
                            data.get("provinceDescription").toString(),
                            data.get("cityDescription").toString(),
                            data.get("barangayDescription").toString(),
                            Long.valueOf(data.get("id").toString()),
                            data.get("code").toString(),
                            data.get("description").toString()
                    );
                    duList.add(dto);
                }
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

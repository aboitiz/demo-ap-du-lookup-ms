package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.*;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.model.City;
import com.apc.du.model.PostalCode;
import com.apc.du.model.Province;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.repository.CityRepository;
import com.apc.du.repository.PostalCodeRepository;
import com.apc.du.repository.ProvinceRepository;
import com.apc.du.services.DULookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DULookupServiceImpl implements DULookupService {

    @Autowired
    private BarangayRepository barangayRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;
    private CityDTO cityDTO;

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

    @Override
    public BaseResponse postalCode(PostalCodeDTO postalCodeDTO) throws APException, IllegalAccessException {
        PostalCode postalCode = null;
        List<String> errArray = new ArrayList<>();
        boolean idChecker = postalCodeDTO.getId() != null;
        if (idChecker) {
            Optional<PostalCode> postalCodeChecker = postalCodeRepository.findById(Long.valueOf(postalCodeDTO.getId().toString()));
            if (postalCodeChecker.isEmpty()) errArray.add(APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());

            if (errArray.size() > 0) {
                return new BaseResponse(errArray)
                    .setStatusCodeMessage(
                            String.valueOf(APIResponse.POSTAL_CODE_NOT_FOUND.getCode()),
                            APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());
            }

            try {
                postalCode = postalCodeChecker.get();
                PostalCode postalCodeTemp = new PostalCode(postalCodeDTO.getCode(), postalCodeDTO.getDescription(), null);
                postalCode.updateEntity(postalCodeTemp);

                postalCodeRepository.save(postalCode);
                return new BaseResponse(postalCodeDTO).setStatusCodeMessage(
                        String.valueOf(APIResponse.SUCCESS.getCode()),
                        APIResponse.SUCCESS.getMessage()
                );
            } catch (Exception e) {
                log.error("Exception occurred while saving City. Message: {}", e.getMessage());
                throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
            }

        } else {
            if (postalCodeDTO.getCode().isBlank() || postalCodeDTO.getDescription().isBlank()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.INVALID_REQUEST_BODY.getCode()),
                        APIResponse.INVALID_REQUEST_BODY.getDescription()
                );
            }
            if (postalCodeDTO.getCode().isBlank()) errArray.add(APIResponse);
            Long count = postalCodeRepository.countByCode(postalCodeDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.EXISTING.getCode()),
                       "Postal Code " + APIResponse.EXISTING.getDescription()
                );
            }

            postalCode = new PostalCode();
            postalCode.setCode(postalCodeDTO.getCode());
            postalCode.setDescription(postalCodeDTO.getDescription());
        }

        try {
            postalCodeRepository.save(postalCode);
            postalCodeDTO.setId(postalCode.getId());
            return new BaseResponse<>(postalCodeDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while saving Postal Code. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse province(ProvinceDTO provinceDTO) throws APException {
        Province province = null;
        boolean idChecker = provinceDTO.getId() != null;
        if (idChecker) {
            // fetch data from db
            Optional<Province> provinceChecker = provinceRepository.findById(Long.valueOf(provinceDTO.getId().toString()));
            if (provinceChecker.isEmpty()) {

            }
            // set data from the DTO or if not provided set data from the default values


        } else {
            if (provinceDTO.getCode().isBlank() || provinceDTO.getDescription().isBlank()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.INVALID_REQUEST_BODY.getCode()),
                        APIResponse.INVALID_REQUEST_BODY.getDescription()
                );
            }
            Long count = provinceRepository.countByCode(provinceDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.EXISTING.getCode()),
                        "Province " + APIResponse.EXISTING.getDescription()
                );
            }

            province = new Province();
            province.setCode(provinceDTO.getCode());
            province.setDescription(provinceDTO.getDescription());
        }

        try {
            provinceRepository.save(province);
            provinceDTO.setId(province.getId());
            return new BaseResponse<>(provinceDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while saving Province. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse city(CityDTO cityDTO) throws APException, IllegalAccessException {
        Province province = null;
        PostalCode postalCode = null;
        City city = null;
        boolean idChecker = cityDTO.getId() != null;
        if (idChecker) {
            List<String> errArray = new ArrayList<>();
            Optional<City> cityChecker = cityRepository.findById(Long.valueOf(cityDTO.getId().toString()));
            Optional<Province> provinceChecker = provinceRepository.findById(Long.valueOf(cityDTO.getProvinceId()));
            Optional<PostalCode> postalCodeChecker = postalCodeRepository.findById(Long.valueOf(cityDTO.getPostalCodeId()));
            if (cityChecker.isEmpty()) errArray.add(APIResponse.CITY_NOT_FOUND.getDescription());
            if (provinceChecker.isEmpty()) errArray.add(APIResponse.PROVINCE_NOT_FOUND.getDescription());
            if (postalCodeChecker.isEmpty()) errArray.add(APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());
            if (errArray.size() > 0) {
                return new BaseResponse(errArray).setStatusCode(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
            }

            city = cityChecker.get();
            City cityTemp = new City(cityDTO.getCode(), cityDTO.getDescription(), province, postalCode, null);
            city.updateEntity(cityTemp);
            try {
                cityRepository.save(city);
                return new BaseResponse(cityDTO).setStatusCodeMessage(
                        String.valueOf(APIResponse.SUCCESS.getCode()),
                        APIResponse.SUCCESS.getMessage()
                );
            } catch (Exception e) {
                log.error("Exception occurred while saving City. Message: {}", e.getMessage());
                throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
            }
        } else {
            if (cityDTO.getCode().isBlank() ||
                cityDTO.getDescription().isBlank() ||
                cityDTO.getProvinceId().isBlank() ||
                cityDTO.getPostalCodeId().isBlank()
            ) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.INVALID_REQUEST_BODY.getCode()),
                        APIResponse.INVALID_REQUEST_BODY.getDescription()
                );
            }

            // need additional check if `province_id` and `postal_code_id` is existing in the db.
            Optional<Province> provinceChecker = null;
            Optional<PostalCode> postalCodeChecker = null;

            provinceChecker = provinceRepository.findById(Long.valueOf(cityDTO.getProvinceId()));
            postalCodeChecker = postalCodeRepository.findById(Long.valueOf(cityDTO.getPostalCodeId()));
            List<String> errArray = new ArrayList<>();
            if (provinceChecker.isEmpty()) errArray.add(APIResponse.PROVINCE_NOT_FOUND.getDescription());
            if (postalCodeChecker.isEmpty()) errArray.add(APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());

            if (errArray.size() > 0) {
                return new BaseResponse(errArray).setStatusCode(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
            }

            province = provinceChecker.get();
            postalCode = postalCodeChecker.get();
            Long count = cityRepository.countByCode(cityDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.EXISTING.getCode()),
                        "City " + APIResponse.EXISTING.getDescription()
                );
            }
        }

        try {
            city = new City();
            city.setCode(cityDTO.getCode());
            city.setDescription(cityDTO.getDescription());
            city.setProvince(province);
            city.setPostalCode(postalCode);
            cityRepository.save(city);

            cityDTO.setId(city.getId());
            cityDTO.setProvinceId(province.getId().toString());
            cityDTO.setPostalCodeId(postalCode.getId().toString());
            return new BaseResponse<>(cityDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while saving City. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
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

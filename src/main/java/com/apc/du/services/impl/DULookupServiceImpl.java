package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.*;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.model.*;
import com.apc.du.repository.*;
import com.apc.du.services.DULookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private DistributionUtilityRepository distributionUtilityRepository;

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
    public BaseResponse postalCode(PostalCodeDTO postalCodeDTO) throws APException {
        try {
            Long count = postalCodeRepository.countByCode(postalCodeDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.POSTAL_CODE_EXISTING.getCode()),
                        APIResponse.POSTAL_CODE_EXISTING.getDescription()
                );
            }

            PostalCode postalCode = new PostalCode();
            postalCode.setCode(postalCodeDTO.getCode());
            postalCode.setDescription(postalCodeDTO.getDescription());
            postalCodeRepository.save(postalCode);
            return new BaseResponse<>(postalCodeDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while creating Postal Code. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse postalCode(PostalCodeDTO postalCodeDTO, Long id) throws APException, IllegalAccessException {
        try {
            Optional<PostalCode> postalCodeChecker = postalCodeRepository.findById(Long.valueOf(id));
            if (postalCodeChecker.isEmpty()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(APIResponse.POSTAL_CODE_NOT_FOUND.getDescription(), String.valueOf(APIResponse.POSTAL_CODE_NOT_FOUND.getCode()));
            }

            PostalCode postalCode = postalCodeChecker.get();
            PostalCode postalCodeTemp = new PostalCode(postalCodeDTO.getCode(), postalCodeDTO.getDescription(), null);
            postalCode.updateEntity(postalCodeTemp);
            postalCodeRepository.save(postalCode);

            return new BaseResponse(postalCodeDTO).setStatusCode(String.valueOf(APIResponse.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("Exception occurred while updating Postal Code. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse province(ProvinceDTO provinceDTO) throws APException {
        try {
            Long count = provinceRepository.countByCode(provinceDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(
                        String.valueOf(APIResponse.PROVINCE_EXISTING.getCode()),
                        APIResponse.PROVINCE_EXISTING .getDescription()
                );
            }

            Province province = new Province();
            province.setCode(provinceDTO.getCode());
            province.setDescription(provinceDTO.getDescription());
            provinceRepository.save(province);
            return new BaseResponse<>(provinceDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while creating Province. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse province(ProvinceDTO provinceDTO, Long id) throws APException, IllegalAccessException {
        try {
            Optional<Province> provinceChecker = provinceRepository.findById(id);
            if (provinceChecker.isEmpty()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(APIResponse.PROVINCE_NOT_FOUND.getDescription(), String.valueOf(APIResponse.PROVINCE_NOT_FOUND.getCode()));
            }

            Province province = provinceChecker.get();
            Province provinceTemp = new Province(provinceDTO.getCode(), provinceDTO.getDescription(), null);
            province.updateEntity(provinceTemp);
            provinceRepository.save(province);

            return new BaseResponse(provinceDTO).setStatusCode(String.valueOf(APIResponse.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("Exception occurred while updating Province. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse city(CityDTO cityDTO) throws APException {
        Map<String, String> errorsMap = new HashMap<>();
        if (cityDTO.getPostalCodeId().toString().isBlank()) errorsMap.put("postalCode", "postalCodeId is required");
        if (cityDTO.getProvinceId().toString().isBlank()) errorsMap.put("provinceId", "provinceId is required");

        if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

        try {
            Optional<PostalCode> postalCodeChecker = postalCodeRepository.findById(cityDTO.getPostalCodeId());
            Optional<Province> provinceChecker = provinceRepository.findById(cityDTO.getProvinceId());
            if (postalCodeChecker.isEmpty()) errorsMap.put("postalCode", APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());
            if (provinceChecker.isEmpty()) errorsMap.put ("province", APIResponse.PROVINCE_NOT_FOUND.getDescription());

            if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

            Long count = cityRepository.countByCode(cityDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(String.valueOf(APIResponse.CITY_EXISTING.getCode()),  APIResponse.CITY_EXISTING.getDescription());
            }

            City city = new City();
            city.setCode(cityDTO.getCode());
            city.setDescription(cityDTO.getDescription());
            city.setPostalCode(postalCodeChecker.get());
            city.setProvince(provinceChecker.get());
            cityRepository.save(city);

            return new BaseResponse<>(cityDTO).setStatusCodeMessage(
                    String.valueOf(APIResponse.SUCCESS_CREATED.getCode()),
                    APIResponse.SUCCESS_CREATED.getMessage()
            );
        } catch (Exception e) {
            log.error("Exception occurred while creating City. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse city(CityDTO cityDTO, Long id) throws APException, IllegalAccessException {
        Map<String, String> errorsMap = new HashMap<>();
        Province province = null;
        PostalCode postalCode = null;
        try {
            if (cityDTO.getProvinceId() != null) {
                if (!cityDTO.getProvinceId().toString().isBlank()) {
                    Optional<Province> provinceChecker = provinceRepository.findById(cityDTO.getProvinceId());
                    if (provinceChecker.isEmpty()) {
                        errorsMap.put ("province", APIResponse.PROVINCE_NOT_FOUND.getDescription());
                    } else {
                        province = provinceChecker.get();
                    }
                }
            }

            if (cityDTO.getPostalCodeId() != null) {
                if (!cityDTO.getPostalCodeId().toString().isBlank()) {
                    Optional<PostalCode> postalCodeChecker = postalCodeRepository.findById(cityDTO.getPostalCodeId());
                    if (postalCodeChecker.isEmpty()) {
                        errorsMap.put("postalCode", APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());
                    } else {
                        postalCode = postalCodeChecker.get();
                    }
                }
            }

            if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

            Optional<City> cityChecker = cityRepository.findById(id);
            if (cityChecker.isEmpty()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(String.valueOf(APIResponse.CITY_NOT_FOUND.getCode()), APIResponse.CITY_NOT_FOUND.getDescription());
            }

            City cityTemp = new City(cityDTO.getCode(), cityDTO.getDescription(), province, postalCode, null);
            City city = cityChecker.get();
            city.updateEntity(cityTemp);
            cityRepository.save(city);

            return new BaseResponse(cityDTO).setStatusCode(String.valueOf(APIResponse.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("Exception occurred while updating City. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse barangay(BarangayDTO barangayDTO) throws APException {
        Map<String, String> errorsMap = new HashMap<>();
        if (barangayDTO.getDescription().toString().isBlank()) errorsMap.put("distributionUtilityId", "postalCodeId is required");
        if (barangayDTO.getCityId().toString().isBlank()) errorsMap.put("cityId", "cityId is required");

        if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

        try {
            Optional<DistributionUtility> distributionUtilityChecker = distributionUtilityRepository.findById(barangayDTO.getDistributionUtilityId());
            Optional<City> cityChecker = cityRepository.findById(barangayDTO.getCityId());
            if (distributionUtilityChecker.isEmpty()) errorsMap.put("distributionUtility", APIResponse.DISTRIBUTION_UTILITY_NOT_FOUND.getDescription());
            if (cityChecker.isEmpty()) errorsMap.put ("city", APIResponse.CITY_NOT_FOUND.getDescription());

            if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

            Long count = barangayRepository.countByCode(barangayDTO.getCode());
            if (count > 0) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(String.valueOf(APIResponse.BARANGAY_EXISTING.getCode()),  APIResponse.BARANGAY_EXISTING.getDescription());
            }

            Barangay barangay = new Barangay();
            barangay.setCode(barangayDTO.getCode());
            barangay.setDescription(barangayDTO.getDescription());
            barangay.setCity(cityChecker.get());
            barangay.setDistributionUtility(distributionUtilityChecker.get());
            barangayRepository.save(barangay);

            return new BaseResponse(barangayDTO).setStatusCodeMessage(String.valueOf(APIResponse.SUCCESS_CREATED.getCode()), APIResponse.SUCCESS_CREATED.getDescription());
        } catch (Exception e) {
            log.error("Exception occurred while creating Barangay. Message: {}", e.getMessage());
            throw new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()), APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public BaseResponse barangay(BarangayDTO barangayDTO, Long id) throws APException, IllegalAccessException {
        Map<String, String> errorsMap = new HashMap<>();
        DistributionUtility distributionUtility = null;
        City city = null;
        try {
            if (barangayDTO.getDistributionUtilityId() != null) {
                if (!barangayDTO.getDistributionUtilityId().toString().isBlank()) {
                    Optional<DistributionUtility> distributionUtilityChecker = distributionUtilityRepository.findById(barangayDTO.getDistributionUtilityId());
                    if (distributionUtilityChecker.isEmpty()) {
                        errorsMap.put ("distributionUtility", APIResponse.DISTRIBUTION_UTILITY_NOT_FOUND.getDescription());
                    } else {
                        distributionUtility = distributionUtilityChecker.get();
                    }
                }
            }

            if (barangayDTO.getCityId() != null) {
                if (!barangayDTO.getCityId().toString().isBlank()) {
                    Optional<City> cityChecker = cityRepository.findById(barangayDTO.getCityId());
                    if (cityChecker.isEmpty()) {
                        errorsMap.put("city", APIResponse.POSTAL_CODE_NOT_FOUND.getDescription());
                    } else {
                        city = cityChecker.get();
                    }
                }
            }

            if (errorsMap.size() > 0) return new BaseResponse(errorsMap).setStatusCodeMessage(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()),  APIResponse.APPLICATION_STATUS_NOT_FOUND.getDescription());

            Optional<Barangay> barangayChecker = barangayRepository.findById(id);
            if (barangayChecker.isEmpty()) {
                return new BaseResponse(new HashMap<>()).setStatusCodeMessage(String.valueOf(APIResponse.BARANGAY_NOT_FOUND.getCode()), APIResponse.BARANGAY_NOT_FOUND.getDescription());
            }

            Barangay barangayTemp = new Barangay(barangayDTO.getCode(), barangayDTO.getDescription(), city, distributionUtility);
            Barangay barangay = barangayChecker.get();
            barangay.updateEntity(barangayTemp);
            barangayRepository.save(barangay);

            return new BaseResponse(barangayDTO).setStatusCode(String.valueOf(APIResponse.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("Exception occurred while updating Barangay. Message: {}", e.getMessage());
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

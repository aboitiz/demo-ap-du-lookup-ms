package com.apc.du.services;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.CityDTO;
import com.apc.du.commons.dto.PostalCodeDTO;
import com.apc.du.commons.dto.ProvinceDTO;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;

public interface DULookupService {
    BaseResponse getDistributionUtility(String province, String city, String barangay, String postalCode) throws ServiceDisconnectedException;

    BaseResponse findAll(String du, String province, String city, String postalCode, String barangay) throws APException;
    BaseResponse postalCode(PostalCodeDTO postalCode) throws APException;

    BaseResponse province(ProvinceDTO province) throws APException;

    BaseResponse city(CityDTO city) throws APException;
}

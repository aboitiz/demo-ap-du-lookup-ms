package com.apc.du.services;

import com.apc.commons.response.BaseResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;

public interface DULookupService { 
    BaseResponse getDistributionUtility(String province, String city, String barangay, String postalCode) throws ServiceDisconnectedException;

    BaseResponse findAll(String du, String province, String city, String postalCode, String barangay) throws APException;
}

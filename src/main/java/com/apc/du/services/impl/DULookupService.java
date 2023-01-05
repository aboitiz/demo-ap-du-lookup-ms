package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.exceptions.ServiceDisconnectedException;

public interface DULookupService {
    BaseResponse<APIErrorResponseDTO> getDistributionUtility(String province, String city, String barangay, String postalCode) throws ServiceDisconnectedException;
}

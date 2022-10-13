package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.exceptions.ServiceDisconnectedException;

public interface DULookupService {


    BaseResponse getDUByCityBarangay(String province,String city, String barangay, String barangayCode) throws ServiceDisconnectedException;

}

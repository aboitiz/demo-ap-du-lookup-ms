package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;

public interface DULookupService {


    BaseResponse getDUByCityBarangay(String city, String barangay, String barangayCode);

}

package com.apc.du.commons.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum APIResponse {

    SUCCESS(200,"","SUCCESS","The request was successful and the response body contains the representation requested."),
    SUCCESS_CREATED(201,"","CREATED","The request has been fulfilled, resulting in the creation of a new resource"),
    GENERAL_DECLINE(500,"UNEXPECTED_ERROR","An internal server error occurred. Please contact your administrator.","The request was failed due to unexpected error"),
    APPLICATION_STATUS_NOT_FOUND(404,"APPLICATION_STATUS_NOT_FOUND", "Service Application Not Found.","The request cannot be completed"),
    SERVICE_DISCONNECTED(503, "SERVICE_DISCONNECTED", "Service Disconnected", "The REST API service or server indicated is unreachable."),

    DU_LOCATION_NOT_FOUND(404, "DU_LOCATION_NOT_FOUND", "Location Not Found", "No Distribution Utility found for the location provided"),
    POSTAL_CODE_NOT_FOUND(404, "POSTAL_CODE_NOT_FOUND", "Postal Code Not Found", "Postal Code Not Found"),
    CITY_NOT_FOUND(404, "CITY_NOT_FOUND", "City Not Found", "City Not Found"),
    PROVINCE_NOT_FOUND(404, "PROVINCE_NOT_FOUND", "Province Not Found", "Province Not Found"),
    BARANGAY_NOT_FOUND(404, "BARANGAY_NOT_FOUND", "Barangay Not Found", "Barangay Not Found"),
    DISTRIBUTION_UTILITY_NOT_FOUND(400, "DISTRIBUTION_UTILITY_NOT_FOUND", "Distribution Utility Not Found", "Distribution Utility Not Found"),
    POSTAL_CODE_EXISTING(400, "POSTAL_CODE_EXISTING", "Postal Code Existing", "Postal Code is already existing"),
    PROVINCE_EXISTING(400, "PROVINCE_EXISTING", "Province Existing", "Province is already existing"),
    CITY_EXISTING(400, "CITY_EXISTING", "City Existing", "City is already existing"),
    BARANGAY_EXISTING(400, "BARANGAY_EXISTING", "Barangay Existing", "Barangay is already existing");

    private final int code;
    private final String message;
    private final String description;
    private final String error;

    APIResponse(int code, String error, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.error = error;
    }
}

*** Settings ***
Library    RequestsLibrary
Library    Collections
Library    JSONLibrary

*** Test Cases ***
Validate List of Locations for barangay
    ${json_obj}=    load json from file     du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequest.brgy_endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_barangay}=     get value from json   ${json_obj}      GETRequest.barangay

    #Validation
    ${response_body}=        set variable          ${response.json()}
    FOR    ${index}    IN RANGE    2
        #log to console    ${index}    # 0-9
       ${brgy_list}=          get value from json     ${response_body}    data[${index}].barangay.description
       run keyword and continue on failure      should contain    ${brgy_list}         ${expected_barangay[0]}
    END


Validate List of Locations for City
    ${json_obj}=    load json from file    du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequest.city_endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_city}=         get value from json   ${json_obj}      GETRequest.city

    #Validation
    ${response_body}=        set variable          ${response.json()}
    FOR    ${index}    IN RANGE    10
        #log to console    ${index}    # 0-43
       ${city_list}=          get value from json     ${response_body}    data[${index}].city.description
       run keyword and continue on failure      should contain       ${city_list}         ${expected_city[0]}
    END

Validate List of Locations for DU
    ${json_obj}=    load json from file    du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequest.du_endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_du}=          get value from json    ${json_obj}       GETRequest.du

    #Validation
    ${response_body}=        set variable          ${response.json()}
    FOR    ${index}    IN RANGE    10
       ${du_list}=          get value from json     ${response_body}    data[${index}].distributionUtility.description
       run keyword and continue on failure      should contain       ${du_list}         ${expected_du[0]}
    END

Validate List of Locations for Postal Code
    ${json_obj}=    load json from file     du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequest.postal_endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_code}=         get value from json    ${json_obj}      GETRequest.code

    #Validation
    ${response_body}=        set variable          ${response.json()}
    FOR    ${index}    IN RANGE    10
       ${code_list}=          get value from json     ${response_body}    data[${index}].postalCode.code
       run keyword and continue on failure      should contain       ${code_list}         ${expected_code[0]}
    END


Validate List of Locations for Province
    ${json_obj}=    load json from file     du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequest.province_endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_province}=     get value from json    ${json_obj}      GETRequest.province

    #Validation
    ${response_body}=        set variable          ${response.json()}
    FOR    ${index}    IN RANGE    10
       ${province_list}=          get value from json     ${response_body}    data[${index}].province.description
       run keyword and continue on failure      should contain       ${province_list}         ${expected_province[0]}
    END

Validate Get Invalid Location
    ${json_obj}=    load json from file     du_locations.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETInvalidRequest.endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}       expected_status=404

    #Value from the TestData file
    ${expected_message}=     get value from json   ${json_obj}     GETInvalidRequest.invalid_result

    #Validation
    ${response_body}=        set variable           ${response.json()}
    ${actual_result}=    get value from json        ${response_body}     message
    should be equal         ${actual_result[0]}           ${expected_message[0]}


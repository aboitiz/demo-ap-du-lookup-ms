*** Settings ***
Library    RequestsLibrary
Library    Collections
Library    JSONLibrary

*** Test Cases ***
Validate Get Distribution Utility Lookup Service - VECO
    ${json_obj}=    load json from file    ${CURRDIR}/du_lookup.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequestVECO.endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_barangay}=     get value from json   ${json_obj}      GETRequestVECO.barangay
    ${expected_city}=         get value from json   ${json_obj}      GETRequestVECO.city
    ${expected_province}=     get value from json    ${json_obj}     GETRequestVECO.province
    ${expected_code}=         get value from json    ${json_obj}     GETRequestVECO.code
    ${expected_du}=          get value from json    ${json_obj}      GETRequestVECO.du

    #Validation
    ${response_body}=        set variable          ${response.json()}
    ${actual_barangay}=     get value from json    ${response_body}     data[0].barangay
    ${actual_city}=         get value from json    ${response_body}     data[0].city
    ${actual_province}=     get value from json    ${response_body}     data[0].province
    ${actual_code}=        get value from json    ${response_body}      data[0].postalCode
    ${actual_du}=          get value from json    ${response_body}      data[0].distributionUtility.description

    should be equal         ${actual_barangay}           ${expected_barangay}
    should be equal         ${actual_city}               ${expected_city}
    should be equal         ${actual_province}           ${expected_province}
    should be equal         ${actual_code}               ${expected_code}
    should be equal         ${actual_du}                ${expected_du}

Validate Get Distribution Utility Lookup Service - DLPC
    ${json_obj}=    load json from file    ${CURDIR}/du_lookup.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETRequestDLPC.endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Validate response status code
    ${status_response}=  convert to string   ${response.status_code}

    #Value from the TestData file
    ${expected_barangay}=     get value from json   ${json_obj}      GETRequestDLPC.barangay
    ${expected_city}=         get value from json   ${json_obj}      GETRequestDLPC.city
    ${expected_province}=     get value from json    ${json_obj}     GETRequestDLPC.province
    ${expected_code}=         get value from json    ${json_obj}     GETRequestDLPC.code
    ${expected_du}=          get value from json    ${json_obj}      GETRequestDLPC.du

    #Validation
    ${response_body}=        set variable          ${response.json()}
    ${actual_barangay}=     get value from json    ${response_body}     data[0].barangay
    ${actual_city}=         get value from json    ${response_body}     data[0].city
    ${actual_province}=     get value from json    ${response_body}     data[0].province
    ${actual_code}=        get value from json    ${response_body}      data[0].postalCode
    ${actual_du}=          get value from json    ${response_body}      data[0].distributionUtility.description

    should be equal         ${actual_barangay}           ${expected_barangay}
    should be equal         ${actual_city}               ${expected_city}
    should be equal         ${actual_province}           ${expected_province}
    should be equal         ${actual_code}               ${expected_code}
    should be equal         ${actual_du}                 ${expected_du}


Validate Get Invalid Location Distribution Utility Lookup Service
    ${json_obj}=    load json from file    ${CURDIR}/du_lookup.json
    ${baseUrl}=     get value from json     ${json_obj}     BaseUrl.baseUrl
    ${endpoint}=        get value from json     ${json_obj}   GETInvalidRequest.endpoint
    create session      usersession   ${baseUrl[0]}
    ${response}=    get on session     usersession     ${endpoint[0]}

    #Value from the TestData file
    ${expected_message}=     get value from json   ${json_obj}     GETInvalidRequest.invalid_result

    #Validation
    ${response_body}=        set variable           ${response.json()}
    ${actual_result}=    get value from json        ${response_body}     data.error

     should be equal         ${actual_result[0]}           ${expected_message[0]}
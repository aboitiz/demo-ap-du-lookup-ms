*** Settings ***
Library    RequestsLibrary
Library    Collections
Library    JSONLibrary

*** Variables ***
${baseUrl}  http://localhost:9001
${requestUrl}    /v1/api/du-lookup/du?barangay=La%20Paz&city=Carmen&province=CEBU
${barangay}=    La Paz
${city}=    Carmen
${province}=    CEBU
${du1}=  VECO
${du2}=  DLPC
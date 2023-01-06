* Settings *
Library    RequestsLibrary
Library    Collections
Library    JSONLibrary

* Variables *
${testdata_location}=   src/test/resources/testdata.json

* Test Cases *
Verify GET Request and validate the response code and response body

    ${json_obj}=    load json from file         ${testdata_location}

    ${title}=     get value from json   ${json_obj}    HelloWorld.title

    log to console    ${title[0]}

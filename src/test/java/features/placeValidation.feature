Feature: Validating Place API's

  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>" "<phone>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created using "GetPlaceAPI"
    When user calls "GetPlaceAPI" with "GET" http request
    Then the API call got success with status code 200
    And "name" in response body is "<name>"

    Examples:
    |name|language|address|phone|
    |SHouse|English|happy center|8210543220|
    |AHouse|French|Hello Ji|8210543210|

  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
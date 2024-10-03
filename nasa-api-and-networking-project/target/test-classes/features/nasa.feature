@NasaTest
Feature: NASA Astronomy Picture of the Day API

  Scenario Outline: Retrieve APOD for a specific date
    Given I have the NASA API key
    When I send a request for the Astronomy Picture of the Day for "<date>"
    Then I should receive a response with status code <expected_status_code>
    And the response should contain the keys: "<expected_keys>"
    And the media_type should match one of the following types: "<expected_type_1>" or "<expected_type_2>"

    Examples: 
      | date       | expected_keys                             | expected_status_code | expected_type_1 | expected_type_2 |
      | yesterday  | title, explanation, url, media_type, date |                  200 | image           | video           |
      | 2023-09-29 | title, explanation, url, media_type, date |                  200 | image           | video           |

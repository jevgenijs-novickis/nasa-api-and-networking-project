@NetworkTests
Feature: Network Operations

  Scenario Outline: Ensure my public IP is not in the restricted range
    Given I retrieve my public IP address
    Then the IP address should not fall within the range from "<range_start>" to "<range_end>"

    Examples: 
      | range_start | range_end   |
      | 101.33.28.0 | 101.33.29.0 |

  Scenario: Resolve google-public-dns-a.google.com
    When I resolve the domain "google-public-dns-a.google.com"
    Then the resolved IP address should be "8.8.8.8"

  Scenario: Perform a traceroute to 8.8.8.8
    When I perform a traceroute to "8.8.8.8"
    Then the target should be reached within 10 hops

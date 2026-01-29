@Regression
Feature: Swag Labs Login Test
Background:
    Given user launches the application
    Then page title should contain "Swag Labs"
@Smoke
Scenario: Give Credentials and verify sucessful Login
  Given user gives the username as "<username>" and password as "<password>" 
  Then the products page should appear
  Examples:
  | username                | password     |
  | standard_user           | secret_sauce |
  | problem_user            | secret_sauce |
  | performance_glitch_user | secret_sauce |
  | error_user              | secret_sauce |
Scenario: Give Invalid Credentials and verify Insucessful Login
  Given user gives the username as "<username>" and password as "<password>" 
  Then then error message should be diplayed 
  Examples:
  | username                | password      |
  | standard_userdhhd       | secret_sauce  |
  | locked_out_user         | secret_sauce1 |
  | problem_user11          | secret_sauce11| 
  
 

  
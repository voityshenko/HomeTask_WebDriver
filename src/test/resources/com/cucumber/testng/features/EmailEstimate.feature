Feature: as a user I want to receive an email with correct calculation

  Background:
    Given the user opens Google Cloud Pricing Calculator

  Scenario Template: TC <id> Total Estimated Cost should be displayed after filling fields and clicking "Add to estimate button"
    And the user enters Number of instances '<Number of instances>'
    And the user selects Series '<seriesMachine>'
    And the user selects Instance type 'CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8'
    And the user selects Add GPUs checkbox
    And the user selects GPU type '<gpuType>'
    And the user selects Number of GPUs '<gpuNumber>'
    And the user selects Local SSD '<localSsd>'
    And the user selects Committed usage '<term>' year
    When the user clicks Add to estimate button
    Then correct Total Estimated Cost is displayed
      | class    | <class>    |
      | instance | <instance> |
      | localSsd | <localSsd> |
      | term     | <term>     |
    And the user emails estimate to a new disposable address
    And the email contains correct Total Estimated Cost

    Examples:
      | id  | Number of instances | gpuType           | gpuNumber | seriesMachine | class   | instance      | localSsd | term |
      | A01 | 4                   | NVIDIA_TESLA_P100 | 2         | n1            | regular | n1-standard-8 | 2x375    | 1    |
      | A02 | 5                   | NVIDIA_TESLA_K80  | 1         | n1            | regular | n1-standard-8 | 1x375    | 3    |
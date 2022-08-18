Feature: Submitting scheduled tasks should be successful

  Scenario: A task should be processed by a single node, not both
    When producer schedules tasks PT2S from now:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 01 | 2023-12-01T10:00:00.000Z | SUBMITTED |
    And producer schedules tasks PT4S from now:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status    |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 02 | 2023-12-02T10:00:00.000Z | SUBMITTED |
    Then within PT1S consumers get tasks:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 01 | 2023-12-01T10:00:00.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 02 | 2023-12-02T10:00:00.000Z | SUBMITTED |
    Then within PT2S consumers get tasks:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status   |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 01 | 2023-12-01T10:00:00.000Z | EXECUTED |
    Then within PT2S consumers get tasks:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status   |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 02 | 2023-12-02T10:00:00.000Z | EXECUTED |


Feature: Submitting scheduled tasks should be successful

  Scenario: All consumer should get the exact same scheduled tasks copy
    When producer posts scheduled tasks:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 01 | 2023-12-01T10:00:00.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 02 | 2023-12-02T10:00:00.000Z | SUBMITTED |
      | 0D894CEF-0405-49E1-9F3B-572BB850CF01 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 03 | 2023-12-03T10:00:00.000Z | SUBMITTED |
      | 838A10E7-29F1-4922-9025-F7616A914D60 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 04 | 2023-12-04T10:00:00.000Z | SUBMITTED |
      | 06D05849-2FD1-4A8D-9289-25BADC28E09C | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 05 | 2023-12-05T10:00:00.000Z | SUBMITTED |
    Then consumers get tasks:
      | id                                   | triggerLocation                                                 | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 01 | 2023-12-01T10:00:00.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 02 | 2023-12-02T10:00:00.000Z | SUBMITTED |
      | 0D894CEF-0405-49E1-9F3B-572BB850CF01 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 03 | 2023-12-03T10:00:00.000Z | SUBMITTED |
      | 838A10E7-29F1-4922-9025-F7616A914D60 | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 04 | 2023-12-04T10:00:00.000Z | SUBMITTED |
      | 06D05849-2FD1-4A8D-9289-25BADC28E09C | http://dsched-broker-e2e-producer-server:8080/api/v1/trigger-me | task 05 | 2023-12-05T10:00:00.000Z | SUBMITTED |


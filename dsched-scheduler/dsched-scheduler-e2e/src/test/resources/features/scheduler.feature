Feature: Submitting scheduled tasks should be successful

  Scenario: A task should be processed by a single node, not both
    When producer schedules tasks, "PT5S" from now:
      | id                                   | triggerLocation                                                | label   | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 01 | SUBMITTED |
    And producer schedules tasks, "PT10S" from now:
      | id                                   | triggerLocation                                                | label   | status    |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 02 | SUBMITTED |
    Then within "PT1S", consumers get tasks:
      | id                                   | triggerLocation                                                | label   | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 01 | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 02 | SUBMITTED |
    Then within "PT5S", consumers get tasks:
      | id                                   | triggerLocation                                                | label   | status   |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 01 | EXECUTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 02 | SUBMITTED |
    Then within "PT5S", consumers get tasks:
      | id                                   | triggerLocation                                                | label   | status   |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 01 | EXECUTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://dsched-scheduler-producer-server:8080/api/v1/trigger-me | task 02 | EXECUTED |


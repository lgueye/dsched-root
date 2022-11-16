Feature: Submitting scheduled tasks should be successful

  Scenario: A task should be processed by a single node, not both
    Given ws consumers subscribe to notifications
    And clock is "2022-09-03T11:00:00.000Z"
    And producer schedules tasks, "PT5S" from now:
      | id                                   | triggerLocation                        | label   | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | SUBMITTED |
    Then ws consumers get notifications:
      | id                                   | triggerLocation                        | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
    And producer schedules tasks, "PT10S" from now:
      | id                                   | triggerLocation                        | label   | status    |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | SUBMITTED |
    Then ws consumers get notifications:
      | id                                   | triggerLocation                        | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
    When unfreeze clock
    Then within "PT1S", consumers get tasks:
      | id                                   | triggerLocation                        | label   | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | SUBMITTED |
    Then ws consumers get notifications:
      | id                                   | triggerLocation                        | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
    Then within "PT5S", consumers get tasks:
      | id                                   | triggerLocation                        | label   | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | EXECUTED  |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | SUBMITTED |
    Then ws consumers get notifications:
      | id                                   | triggerLocation                        | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | EXECUTED  |
    Then within "PT10S", consumers get tasks:
      | id                                   | triggerLocation                        | label   | status   |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | EXECUTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | EXECUTED |
    Then ws consumers get notifications:
      | id                                   | triggerLocation                        | label   | triggerAt                | status    |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | SUBMITTED |
      | 78866723-506D-4F83-A8DA-B581E5819E1A | http://reverse-proxy/api/v1/trigger-me | task 01 | 2022-09-03T11:00:05.000Z | EXECUTED  |
      | 2364944D-48DF-4282-8778-3A4048A52F68 | http://reverse-proxy/api/v1/trigger-me | task 02 | 2022-09-03T11:00:10.000Z | EXECUTED  |


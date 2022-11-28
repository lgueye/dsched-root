# Build

`mvn clean package`

Will execute unit tests (surefire plugin)

# Run integration tests and e2e tests

`mvn clean verify`

Will execute e2e and integration tests (failsafe plugin). Any test ending with `IT` will be picked.

Main phases involved:

- `pre-integration-tests`: used to execute any setup required for tests (ie spawn docker containers)
- `verify`: used to execute tests (`*IT` pattern). Failsafe default phase 
- `post-integration-tests`: used to tear down any resource used by tests (ie kill docker containers)

Docker compose is used in `pre` (compose up) and `post` (compose down) integration test phases. 

# Run demo

### Start backends + frontend

- At the root of the project, first build to make sure all backends are up to date

`mvn clean package`

- Then change directory to the demo project and start the full stack

`cd dsched-demo && mvn package -Pdemo`

### Visit UI

At this point no event were created so the ui would get no notification. You can witness it at http://localhost:5000

### Create schedules

The script create-schedule.sh creates a single scheduled task with the below specs:

- should trigger 60 s from the creation time
- instructed to trigger the job located at http://localhost:7000/api/v1/trigger-me

### Verify

A task has 2 status:

- `SUBMITTED`: initial state of a task. All scheduler nodes will receive the submitted task. All nodes will send a notification
- `EXECUTED`: once the scheduler has triggered the job. Only a single scheduler will execute the job and send a notification

Visit the UI at http://localhost:5000.

Before the task execution you should see as many notification as scheduler nodes and their status should be `SUBMITTED`

As soon as the task is executed you should see an additional notification as `EXECUTED`






# Build

`mvn clean package`

Will execute unit tests (surefire plugin)

# Run integration tests and e2e tests

`docker stop $(docker ps -a -q) ; docker system prune -f ; mvn clean verify`

Will execute e2e and integration tests (failsafe plugin). Any test ending with `IT` will be picked.

Main phases involved:

- `pre-integration-tests`: used to execute any setup required for tests (ie spawn docker containers)
- `verify`: used to execute tests (`*IT` pattern). Failsafe default phase 
- `post-integration-tests`: used to tear down any resource used by tests (ie kill docker containers)

Docker compose is used in `pre` (compose up) and `post` (compose down) integration test phases. 

# Run demo

### Start backends + frontend

- At the root of the project, run the below command (will build and start the frontend+backends)

`docker stop $(docker ps -a -q) ; docker system prune -f ; mvn clean package -Pdemo`

### Visit UI

At this point no event were created so the ui would get no notification. You can witness it at `http://localhost:5000`

### Create schedules

`./create-scheduled-task.sh`

The script `create-scheduled-task.sh` creates a single scheduled task with the below specs:

- should trigger 60 s from the creation time
- instructed to trigger the job located at http://reverse-proxy/api/v1/trigger-me

### Verify

A task has 2 status:

- `SUBMITTED`: initial state of a task. All scheduler nodes will receive the submitted task. All nodes will send a notification
- `EXECUTED`: once the scheduler has triggered the job. Only a single scheduler will execute the job and send a notification

Visit the UI at `http://localhost:5000`.

Before the task gets executed, you should see as many notification as scheduler nodes and their status should be `SUBMITTED`

As soon as the task was executed, you should see an additional notification as `EXECUTED`

### Tear down

`docker stop $(docker ps -a -q) ; docker system prune -f`

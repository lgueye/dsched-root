# Build

`mvn clean package`

Will execute unit tests (surefire plugin)

# Run integration tests and e2e tests

`mvn clean verify`

Will execute e2e and integration tests (failsafe plugin). Any test ending with `IT` will be picked.

Main phases involved:

- `pre-integration-tests`: used to execute any setup required for tests (ie spawn docker containers)
- `verify`: used to execute tests (`*IT` pattern). this is failsafe the default phase 
- `post-integration-tests`: used to tear down any resource required for tests (ie kill docker containers)

Docker compose is used in `pre` (compose up) and post (compose down) integration test phases. 

# Run demo

## Start backends + frontend

## Visit UI

## Create schedules

## Verify





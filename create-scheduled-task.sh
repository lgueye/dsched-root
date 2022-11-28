#!/bin/bash

UUID=$(uuid)
TRIGGER_AT=$(date -u --date="+1 min" +'%Y-%m-%dT%H:%M:%S.000%:z')

TASK="{\"status\":\"SUBMITTED\", \"triggerLocation\":\"http://reverse-proxy/api/v1/trigger-me\", \"label\":\"label for $UUID\", \"triggerAt\":\"$TRIGGER_AT\"}"
echo "$TASK"
curl -i -H 'Content-Type: application/json' -XPUT "http://localhost:7000/api/v1/schedules/${UUID}" -d "$TASK"
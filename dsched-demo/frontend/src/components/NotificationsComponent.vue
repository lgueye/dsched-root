<template>
    <div class="row d-flex justify-content-center mt-70 mb-70">
        <div class="col-md-6">
            <div class="main-card mb-3 card">
                <div class="card-body">
                    <h5 class="card-title">Notifications timeline</h5>
<!--                    <div class="vertical-timeline vertical-timeline&#45;&#45;animate vertical-timeline&#45;&#45;one-column">-->
<!--                        <div class="vertical-timeline-item vertical-timeline-element">-->
<!--                            <div>-->
<!--                                <span class="vertical-timeline-element-icon bounce-in">-->
<!--                                    <i class="badge badge-dot badge-dot-xl badge-warning"></i>-->
<!--                                </span>-->
<!--                                <div class="vertical-timeline-element-content bounce-in text-right">-->
<!--                                    <h4 class="timeline-title">41E17752-6FCD-11ED-88F0-071E3F654C98</h4>-->
<!--                                  <p><span class="text-warning font-weight-bold">SCHEDULED</span> by scheduler-consumer-server-02</p>-->
<!--                                    <span class="vertical-timeline-element-date pr-50">-->
<!--                                      11:04-->
<!--                                      <div class="vertical-timeline-element-date-dot bg-warning"></div>-->
<!--                                    </span>-->
<!--                                </div>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </div>-->
                    <div class="vertical-timeline vertical-timeline--animate vertical-timeline--one-column" v-for="event in notifs" :key="event.time">
                        <div class="vertical-timeline-item vertical-timeline-element">
                            <div>
                                <span class="vertical-timeline-element-icon bounce-in">
                                    <i class="badge badge-dot badge-dot-xl badge-success" v-if="event.task.status === 'SUBMITTED'"></i>
                                    <i class="badge badge-dot badge-dot-xl badge-warning" v-else></i>
                                </span>
                                <div class="vertical-timeline-element-content bounce-in pr-5 text-right">
                                    <h4 class="timeline-title">{{ event?.task?.id }}</h4>
                                    <p v-if="event.task.status === 'SUBMITTED'"><span class="text-warning font-weight-bold">SCHEDULED</span> by {{ event?.node }}</p>
                                    <p v-else><span class="text-success font-weight-bold">{{ event?.task?.status }}</span> by scheduler node #{{ event?.node }}</p>
                                    <span class="vertical-timeline-element-date">
                                      {{ event?.time }}
                                      <div class="vertical-timeline-element-date-dot bg-warning" v-if="event.task.status === 'SUBMITTED'"></div>
                                      <div class="vertical-timeline-element-date-dot bg-success" v-else></div>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'
import { DateTime } from 'luxon'

export default {
  name: 'NotificationsComponent',
  setup () {
    const scheduledTaskNotifications = ref([])
    const serverUrl = 'http://localhost:9080/ws/v1/scheduled-notifications'
    const socket = new SockJS(serverUrl)
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, function () {
      stompClient.subscribe('/topic/scheduled-notifications', function (data) {
        const notification = JSON.parse(data.body)
        console.log('Message from server ', notification)
        notification.time = DateTime.fromISO(notification.time).toFormat('HH:mm')
        notification.node = notification.node.substr(notification.node.length - 2)
        console.log('Message to UI ', notification)
        scheduledTaskNotifications.value.push(notification)
      })
    })

    return { notifs: scheduledTaskNotifications.value.reverse() }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body{
  background-color: #eee;
}

.mt-70{
  margin-top: 70px;
}

.mb-70{
  margin-bottom: 70px;
}

.card {
  box-shadow: 0 0.46875rem 2.1875rem rgba(4,9,20,0.03), 0 0.9375rem 1.40625rem rgba(4,9,20,0.03), 0 0.25rem 0.53125rem rgba(4,9,20,0.05), 0 0.125rem 0.1875rem rgba(4,9,20,0.03);
  border-width: 0;
  transition: all .2s;
}

.card {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: 1px solid rgba(26,54,126,0.125);
  border-radius: .25rem;
}

.card-body {
  flex: 1 1 auto;
  padding: 1.25rem;
}
.vertical-timeline {
  width: 100%;
  position: relative;
  padding: 1.5rem 0 1rem;
}

.vertical-timeline::before {
  content: '';
  position: absolute;
  top: 0;
  left: 67px;
  height: 100%;
  width: 4px;
  background: #e9ecef;
  border-radius: .25rem;
}

.vertical-timeline-element {
  position: relative;
  margin: 0 0 1rem;
}

.vertical-timeline--animate .vertical-timeline-element-icon.bounce-in {
  visibility: visible;
  animation: cd-bounce-1 .8s;
}
.vertical-timeline-element-icon {
  position: absolute;
  top: 0;
  left: 60px;
}

.vertical-timeline-element-icon .badge-dot-xl {
  box-shadow: 0 0 0 5px #fff;
}

.badge-dot-xl {
  width: 18px;
  height: 18px;
  position: relative;
}
.badge:empty {
  display: none;
}

.badge-dot-xl::before {
  content: '';
  width: 10px;
  height: 10px;
  border-radius: .25rem;
  position: absolute;
  left: 50%;
  top: 50%;
  margin: -5px 0 0 -5px;
  background: #fff;
}

.vertical-timeline-element-content {
  position: relative;
  margin-left: 90px;
  font-size: .8rem;
}

.vertical-timeline-element-content .timeline-title {
  font-size: .8rem;
  text-transform: uppercase;
  margin: 0 0 .5rem;
  padding: 2px 0 0;
  font-weight: bold;
}

.vertical-timeline-element-content .vertical-timeline-element-date {
  display: block;
  position: absolute;
  left: -90px;
  width: 75px;
  top: 0;
  padding-right: 50px;
  text-align: right;
  color: #adb5bd;
  font-size: .7619rem;
  white-space: nowrap;
}

.vertical-timeline-element-date-dot {
  content: "";
  width: 10px;
  height: 10px;
  border-radius: 3px;
  position: absolute;
  right: 0;
  top: 7px;
}

</style>

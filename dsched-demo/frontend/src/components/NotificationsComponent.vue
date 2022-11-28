<template>
    <div
            id="slider"
            class="slide-in orange"
            v-for="event in notifs"
            :key="event.time">
        <ul>
            <p>
                {{ event?.time }} - {{ event?.node }} - {{ event?.task?.status }}
            </p>
            <li>
                {{ event?.task?.id }}
            </li>
            <li>
                {{ event?.task?.label }}
            </li>
            <li>
                {{ event?.task?.triggerLocation }}
            </li>
        </ul>
    </div>
</template>

<script>
import { ref } from 'vue'
import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

export default {
  name: 'NotificationsComponent',
  setup () {
    const scheduledTaskNotifications = ref([
    ])
    const serverUrl = 'http://localhost:9080/ws/v1/scheduled-notifications'
    const socket = new SockJS(serverUrl)
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, function () {
      stompClient.subscribe('/topic/scheduled-notifications', function (data) {
        console.log('Message from server ', data.body)
        scheduledTaskNotifications.value.push(data.body)
      })
    })

    return { notifs: scheduledTaskNotifications }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    body {
        cursor: default;
        font-size: 14px;
        line-height: 21px;
        font-family: 'Segoe UI', 'Helvetica', Garuda, Arial, sans-serif;
        padding: 18px 18px 18px 18px;
    }

    ul {
        margin-bottom: 14px;
        list-style: none;
    }

    li {
        /* width: 300px; */
        height: 30px;
        margin: 0 0 7px 0;
    }

    .orange {
        border-left: 5px solid #f5876e;
    }

    #slider {
        /* position: absolute; */
        transform: translateX(-100%);
        -webkit-transform: translateX(-100%);
    }

    .slide-in {
        animation: slide-in 0.5s forwards;
        -webkit-animation: slide-in 0.5s forwards;
    }

    .slide-out {
        animation: slide-out 0.5s forwards;
        -webkit-animation: slide-out 0.5s forwards;
    }

    @keyframes slide-in {
        100% {
            transform: translateX(0%);
        }
    }

    @-webkit-keyframes slide-in {
        100% {
            -webkit-transform: translateX(0%);
        }
    }

    @keyframes slide-out {
        0% {
            transform: translateX(0%);
        }
        100% {
            transform: translateX(-100%);
        }
    }

    @-webkit-keyframes slide-out {
        0% {
            -webkit-transform: translateX(0%);
        }
        100% {
            -webkit-transform: translateX(-100%);
        }
    }
</style>

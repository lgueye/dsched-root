<template>
    <div id="app">
        My tasks
        <notifications/>
    </div>
</template>

<script>
import notifications from './components/NotificationsComponent.vue'

export default {
  name: 'App',
  components: {
    notifications
  }
}
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>

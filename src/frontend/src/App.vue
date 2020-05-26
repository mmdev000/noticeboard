<template>
  <div id="app">
    <HelloWorld msg="board"/>
  </div>
</template>

<script>
import HelloWorld from './components/BoardList.vue'
import request from "request"

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  mounted() {
    request('http://localhost:8080', function (error, response, body) {
      window.console.log('error:', error);
      window.console.log('statusCode:', response && response.statusCode);
      window.console.log('body:', body);
    });
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

import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import router from "./components/router"

Vue.component('simple-pagination')
Vue.component('BoardCreate')
Vue.prototype.$http = axios
Vue.use(BootstrapVue)
Vue.config.productionTip = false

new Vue({
  router,
  el: '#app',
  template: '<App/>',
  components: { App },
  render: h => h(App),
}).$mount('#app')

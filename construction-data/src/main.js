import Vue from 'vue'
import App from './App.vue'

import './assets/common.less'

import dataV from '@jiaminghi/data-view'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Axios from 'axios'

// 将axios挂载到原型上
Vue.prototype.$axios = Axios

Vue.config.productionTip = false

Vue.use(dataV)
Vue.use(ElementUI)

new Vue({
  render: h => h(App)
}).$mount('#app')

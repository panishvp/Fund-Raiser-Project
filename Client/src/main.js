// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'vue-tel-input/dist/vue-tel-input.css'
import 'vue-phone-number-input/dist/vue-phone-number-input.css'
import VeeValidate from 'vee-validate'
import Amplify, * as AmplifyModules from 'aws-amplify'
import {AmplifyPlugin} from 'aws-amplify-vue'
import awsExports from './aws-exports'
import {store} from './store.js'

Amplify.configure(awsExports)
Vue.use(AmplifyPlugin, AmplifyModules)

Vue.config.productionTip = false

Vue.use(Vuetify)
Vue.use(VeeValidate)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

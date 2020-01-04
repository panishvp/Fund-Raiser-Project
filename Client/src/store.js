
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    user: null,
    signedIn: false,
    causeId: 0,
    causeData: [],
    API_URL: 'http://localhost:8085/',
    jwt: '',
    categories: [],
    isAdmin: false,
    userEmail: ''

  },
  mutations: {

  },
  actions: {

  }
})
window.store = store

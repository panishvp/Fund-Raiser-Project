<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-toolbar fixed class="cyan">
    <v-toolbar-title class="mr-4">
          <span @click="navigateTo({name: 'home' })" class="home"> Fund Raiser
          </span>
    </v-toolbar-title>
    <v-toolbar-items v-if = "this.$store.state.isAdmin" >
      <v-btn flat dark @click="navigateTo({name: 'createcause' })"> Create Cause </v-btn>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn flat dark @click="navigateTo({name: 'about' })"> About US </v-btn>
    </v-toolbar-items>
    <v-toolbar-items>
      <v-menu open-on-hover offset-y v-if = "this.$store.state.signedIn" >
        <template v-slot:activator="{ on }">
          <v-btn
            dark
            flat
            v-on="on">
            {{getUserNameTitle()}}
          </v-btn>
        </template>
        <v-list>
          <v-list-tile
            v-for="(item, index) in items"
            @click="performAction(item.title)"
            :key="index"
          >
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile>
        </v-list>
      </v-menu>
      <v-btn v-if = "!this.$store.state.signedIn" flat dark @click="navigateTo({name: 'testLogin' })">
        Login
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>

import { Auth } from 'aws-amplify'
import ApiService from '../services/LoginLogoutService'
// import { AmplifyEventBus } from 'aws-amplify-vue'

export default {
  data () {
    return {
      items: [
        {title: 'Sign out'}
      ],
      isLogOutSuccess: false,
      username: this.getUserNameTitle()
    }
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    performAction: async function (titleSelected) {
      if (titleSelected === 'Dashboard') {
        this.navigateTo({name: 'dashboard'})
      } else if (titleSelected === 'Sign out') {
        console.log('JWT Token ' + this.$store.state.jwt)
        Auth.signOut()
        const resp = await ApiService.getLogout(this.$store.state.jwt)
        console.log(resp)
        this.$store.state.jwt = null
        this.$store.state.signedIn = false
        this.$store.state.isAdmin = false
        this.$store.state.user = null
        this.redirectToHome()
      }
    },
    redirectToHome () {
      if (this.$store.state.jwt === null) { this.$store.state.signedIn = false }
      this.$store.state.isAdmin = false
      this.$store.state.signedIn = false
      this.navigateTo({name: 'home'})
    },
    getUserNameTitle () {
      var str = this.$store.state.userEmail.toString().split('@')[0]
      console.log(str + 'String')
      return str
    }
  }
}
</script>

<style>
  .home{
    cursor: pointer;
  }
</style>

<template>
  <div>
    <div v-if="!this.$store.state.signedIn">
      <amplify-authenticator></amplify-authenticator>
    </div>
    <div v-if="this.$store.state.signedIn">
      <amplify-sign-out></amplify-sign-out>
    </div>
  </div>
</template>

<script>
import { Auth } from 'aws-amplify'
import { AmplifyEventBus } from 'aws-amplify-vue'
import ApiService from '../services/LoginLogoutService'

export default {
  name: 'testLogin',
  data () {
    return {
      signedIn: this.$store.state.signedIn
    }
  },
  created () {
    this.findUser()
    AmplifyEventBus.$on('authState', info => {
      if (info === 'signedIn') {
        this.findUser()
      } else {
        this.signedIn = false
        this.$store.state.signedIn = false
      }
    })
  },
  methods: {
    async findUser () {
      try {
        const user = await Auth.currentAuthenticatedUser()
        this.$store.state.signedIn = true
        const userEmail = user.attributes.email
        this.$store.state.userEmail = userEmail
        console.log(this.$store.state.userEmail)
        const jwt = user
          .getSignInUserSession()
          .getIdToken()
          .getJwtToken()
        this.$store.state.jwt = jwt
        const resp = await ApiService.getLogin(jwt, userEmail)
          .then(this.navigateTo({name: 'home'}))
          .catch(e => {
            this.errors.push(e)
          })
        console.log(resp)
      } catch (err) {
        this.signedIn = false
      }
    },
    navigateTo (route) {
      this.$router.push(route)
    }
  }

}

</script>

<style scoped>

</style>

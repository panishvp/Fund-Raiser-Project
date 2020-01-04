<template>
  <v-layout justify-center>
    <v-flex xs12>
      <v-toolbar class="cyan">
        <v-toolbar-title class="home_title">Discover</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>search</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card>
        <v-container
          fluid
          grid-list-md
        >
          <v-layout row wrap>
            <v-flex
              v-for="cause in causes"
              :key="cause.causeTitle"
              v-bind="{ [`xs${v_binding_flex}`]: true }">
              <v-card>
                <v-img
                  v-bind:src="require('@/assets/'+'1.png')"
                  height="200px"
                >
                  <v-container
                    fill-height
                    fluid
                    pa-2
                  >
                    <v-layout fill-height>
                      <v-flex xs12 align-end flexbox>
                        <span class="headline white--text" v-text="cause.causeTitle"></span>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-img>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn>
                    <router-link v-bind:to="'/cause_details/' + cause.causeId">More</router-link>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'
import CauseDetails from '@/components/CauseDetails'
import axios from 'axios'
export default {
  name: 'UserHome',
  components: {
    Panel,
    CauseDetails
  },
  data () {
    return {
      causes: [],
      v_binding_flex: 6,
      causeId: '123'
    }
  },
  mounted () {
    // this.causes = CauseService.getAllCauses().toJSON
    axios
      .get('http://localhost:8085/cause/getAllActiveCauses')
      .then(response => (this.causes = response.data))
  },
  methods: {
    async cause_detail (cause) {
      this.$router.push('cause_details', cause)
    },
    navigateTo (route) {
      this.$router.push(route)
    }
  }
}
</script>

<style>
  .home_title{
    color: white;
  }
</style>

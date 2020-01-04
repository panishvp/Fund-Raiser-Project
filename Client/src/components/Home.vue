<template>
  <v-layout justify-center>
    <v-flex xs12>
      <v-toolbar class="cyan">
        <v-toolbar-title class="home_title">Discover</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-flex xs12 sm6 md3>
          <v-text-field
            class="mx-3 search_field"
            flat
            label="Search"
            solo
            v-model="cause_name"
            @change="searchCausefilter"
          ></v-text-field> </v-flex>
        <v-flex xs12 sm6 md3>
          <v-select
            class="mx-3 search_field"
            :items="this.$store.state.categories"
            item-value="categoryValue"
            item-text="categoryName"
            label="Category"
            @change="searchCausefilter"
            name="search_category"
            solo></v-select>
        </v-flex>
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
                  v-bind:src="require('@/assets/'+ cause.imageName)"
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
import GetCauseService from '@/services/CauseService'
import ApiService from '../services/LoginLogoutService'

export default {
  name: 'Home',
  components: {
    Panel,
    CauseDetails
  },
  created () {
    console.log('Start home created ' + this.$store.state.isAdmin)
    this.isAdmin()
    console.log('End home created ' + this.$store.state.isAdmin)
  },
  data () {
    return {
      causes: [],
      v_binding_flex: 6,
      causeId: '123',
      cause_name: ' ',
      category: [],
      searchedCauses: []
    }
  },
  async mounted () {
    this.causes = await GetCauseService.getAllCauses()
    this.$store.state.categories = await GetCauseService.getAllCategories()
    this.loadCategories()
  },
  methods: {
    async cause_detail (cause) {
      this.$router.push('cause_details', cause)
    },
    loadCategories () {
      this.category = this.$store.state.categories
      this.category.push({ categoryId: 4, categoryName: 'All', imageName: ' ' })
    },
    navigateTo (route) {
      this.$router.push(route)
    },
    async searchCausefilter (searchCategory) {
      console.log('causename' + this.cause_name)
      console.log('causecat' + searchCategory)
      if (searchCategory === 'All') {
        this.searchedCauses = await GetCauseService.searchCauseById(this.cause_name, searchCategory)
      } else if (this.cause_name.trim() === '' && searchCategory !== 'All') {
        this.searchedCauses = await GetCauseService.searchCauseById(searchCategory)
      } else if (this.cause_name.trim() != null && searchCategory != null) {
        this.searchedCauses = await GetCauseService.searchCauseByNameAndId(this.cause_name, searchCategory)
      } else if (searchCategory.isEmpty()) {
        this.searchedCauses = await GetCauseService.searchCauseByName(this.cause_name)
      }
      this.causes = this.searchedCauses.data
      console.log('after search' + this.searchedCauses)
      console.log(this.causes)
    },
    async isAdmin () {
      const resp = await ApiService.getIsAdmin(this.$store.state.jwt)
      console.log(resp.data)
      this.$store.state.user = resp.data
      console.log('checking is admin')
      console.log(this.$store.state.user.userDetails.userType)
      if (this.$store.state.user.userDetails.userType === 'admin') {
        console.log('in if admin')
        this.$store.state.isAdmin = true
        console.log(this.$store.state.isAdmin)
        console.log(this.$store.state.signedIn)
      }
    }
  }
}
</script>

<style>
  .home_title{
    color: white;
  }
  .search_field {
    margin: 10px auto auto;
    border: 1px;
  }
</style>

<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-layout aria-rowcount="center">
    <v-flex xs6 offset-xs3>
      <div class="white elevation-2">
        <v-toolbar flat dense class="cyan" dark>
          <v-toolbar-title>Create a Cause</v-toolbar-title>
        </v-toolbar>
        <form @submit.prevent="createCause">
        <div class="pl-4 pr-4 pb-2 pt-2">
          <br>
          <v-text-field
            name="title"
            placeholder="Enter the title of the Cause"
            label="Title"
            id="cause_title"
            v-model="cause_title"
            v-validate="'min:5|max:100'"
            ></v-text-field>
          <span v-show="errors.has('title')" class="error--text">{{ errors.first('title') }}</span>
          <br>
          <v-select
            :items="this.$store.state.categories"
            label="Category"
            item-text="categoryName"
            item-value="categoryId"></v-select>
          <br>
          <v-text-field
            name="description"
            label="Description"
            placeholder="Enter the Description of the Cause"
            id="description"
            multi-line
            v-model="description"
            v-validate="'min:50|max:500'"
          ></v-text-field>
          <span v-show="errors.has('description')" class="error--text">{{ errors.first('description') }}</span>
          <v-text-field
            name="targeted amount"
            label="Targeted Amount"
            placeholder="Enter the targeted amount"
            id="cause_target_amt"
            v-model="cause_target_amt"
            v-validate="'numeric|min_value:1|max_value:10000'"
          ></v-text-field>
          <span v-show="errors.has('targeted amount')" class="error--text">{{ errors.first('targeted amount') }}</span>
          <br>
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="cause_expiration_date"
                label="Expiration date"
                prepend-icon="event"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              ref="picker"
              name="date"
              v-model="cause_expiration_date"
              :min="new Date().toISOString().substr(0, 10)"
              max="2025-01-01"
              v-validate="'required'"
            ></v-date-picker>
          </v-menu>
          <br>
          <v-text-field
          placeholder="Please enter the Account Number"
          name="account number"
          label="Account number"
          id="account_number"
          v-model="account_number"
          v-validate="'digits:20'"
          ></v-text-field>
          <span v-show="errors.has('account number')" class="error--text">{{ errors.first('account number') }}</span>
          <br>
          <v-text-field
            name="BIC Code"
            label="BIC Code"
            id="code"
            v-model="code"
            v-validate="'min:8|max:8|alpha_num'"
          ></v-text-field>
          <span v-show="errors.has('BIC Code')" class="error--text">{{ errors.first('BIC Code') }}</span>
          <br>
          <v-btn
            class="primary"
            :disabled="!formIsValid"
            type="submit">Create Cause</v-btn>
        </div>
        </form>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
import GetCauseService from '../services/CauseService'
export default {
  data () {
    return {
      cause_title: '',
      category: this.$store.state.categories,
      description: '',
      cause_target_amt: '',
      cause_expiration_date: null,
      account_number: '',
      code: '',
      status: ' ',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Minimum 8 characters',
        minLength: v => v.length >= 5 || 'Minimum 100 Words'
      }
    }
  },
  computed: {
    formIsValid () {
      return this.cause_title !== '' &&
          this.category !== '' &&
          this.description !== '' &&
          this.cause_target_amt !== '' &&
          this.cause_expiration_date !== '' &&
          this.account_number !== '' &&
          this.code !== ''
    }
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async createCause () {
      if (!this.formIsValid) {
        return
      }

      const causedata = {
        'categoryId': 2,
        'causeTargetAmt': Number(this.cause_target_amt),
        'causeExpirationDate': this.cause_expiration_date,
        'description': this.description,
        'collectedAmt': 0,
        'causeTitle': this.cause_title,
        'isActive': true,
        'bankAccountNumber': this.account_number,
        'bic': this.code,
        'imageName': '2.png'
      }
      await GetCauseService.creatingCause(this.$store.state.jwt, causedata)
        .then(response => (this.status = response.data))
      this.navigateTo({ name: 'home' })
    }
  }
}
</script>

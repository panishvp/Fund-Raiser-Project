<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-footer fixed class="cyan footer_align" dark
            height="auto">
    <v-card
      class="flex"
      flat
      tile
    >

      <v-card-actions class="grey darken-3 justify-center">
        &copy;2019 — <strong>Fund Raiser</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    icons: [
      'fab fa-facebook',
      'fa fa-twitter',
      'fab fa-google-plus',
      'fab fa-linkedin',
      'fab fa-instagram'
    ]
  })
}
</script>

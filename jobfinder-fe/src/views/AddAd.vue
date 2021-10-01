<template>
  <div>
    <b-form @submit="SaveAdDB" @reset="onReset" >
      <b-form-group
        id="title-group"
        label="Title:"
        label-for="title"
      >
        <b-form-input
          id="title"
          type="Title"
          v-model="form.title"
          placeholder="Enter title"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="company-group" label="Your Company Name:" label-for="company">
        <b-form-input
          id="company"
          v-model="form.company_name"
          placeholder="Enter company name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="description-group" label="Description:" label-for="description">
        <b-form-input
          id="description"
          v-model="form.description"
          placeholder="Enter description"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary" v-on:click="$router.push('ad')">Submit your ad</b-button> |
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
import AdService from '../services/ad-service'
export default {
  name: 'AddAd',
  data () {
    return {
      form: {
        id: '',
        title: '',
        description: '',
        company_name: ''
      }
    }
  },
  methods: {
    setData (response) {
      this.form = response.data
    },
    SaveAdDB () {
      AdService.saveAdDB(this.form).then(
        response => {
          console.log(response)
          this.form = response.data.ad
          this.message = response.data.message
        },
        error => {
          error.message =
              error.data.message.toString() ||
              error.message ||
              (error.response && error.response.data)
        }
      )
    }
  },
  onReset (event) {
    event.preventDefault()
    // Reset our form values
    this.form.title = ''
    this.form.company_name = ''
    this.form.description = ''
  }
}
</script>
<style scoped>
</style>

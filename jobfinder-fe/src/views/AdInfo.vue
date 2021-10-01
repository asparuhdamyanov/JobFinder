<template>
  <div>
  <h3>{{message}}</h3>
  <input type="text" v-model="ad.title">
  <input type="text" v-model="ad.description">
  <input type="text" v-model="ad.company_name">
  <br>
  </div>
</template>

<script>
import AdService from '../services/ad-service'

export default {
  name: 'AdInfo',
  data () {
    return {
      message: '',
      ad: {
        id: '',
        title: '',
        description: '',
        company_name: ''
      }
    }
  },
  beforeRouteEnter (to, from, next) {
    if (to.params.id) {
      AdService.getAdById(to.params.id).then(
        response =>
          next(vm =>
            vm.setData(response))
      )
    } else {
      next()
    }
  },
  methods: {
    setData (response) {
      this.ad = response.data
    }
  }
}

</script>

<style scoped>

</style>

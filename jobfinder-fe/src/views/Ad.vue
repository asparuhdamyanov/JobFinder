<template>
  <div>
   <div class="form-check">
     <input @click='roleToggle = true' class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
     <label class="form-check-label" for="flexRadioDefault1">
       Employer role
     </label>
   </div>
   <div class="form-check">
     <input @click='roleToggle = false' class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
     <label class="form-check-label" for="flexRadioDefault2">
       Employee role
     </label>
   </div>
   <b-col lg="6" class="my-1">
          <b-form-group
            label="Filter"
            label-for="filter-input"
            label-cols-sm="3"
            label-align-sm="right"
            label-size="sm"
            class="mb-0"
          >
            <b-input-group size="sm">
              <b-form-input
                id="filter-input"
                v-model="filter"
                type="search"
                placeholder="Type to Search"
              ></b-form-input>

              <b-input-group-append>
                <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>

         <b-col sm="5" md="6" class="my-1">
          <b-form-group
            label="Per page"
            label-for="per-page-select"
            label-cols-sm="6"
            label-cols-md="4"
            label-cols-lg="3"
            label-align-sm="right"
            label-size="sm"
            class="mb-0"
           >
          <b-form-select
            id="per-page-select"
            v-model="perPage"
            :options="pageOptions"
            size="sm"
          ></b-form-select>
            </b-form-group>
            </b-col>

          <b-col sm="7" md="6" class="my-1">
            <b-pagination
              v-model="currentPage"
              :total-rows="totalRows"
              :per-page="perPage"
              align="fill"
              size="sm"
              class="my-0"
             ></b-pagination>
          </b-col>
    <b-table
      striped
      hover
      bordered
      :items="ads"
      :fields="fields"
      :filter="filter"
      :filter-included-fields="filterOn"
      :current-page="currentPage"
      stacked="md"
      :per-page="perPage"
      show-empty
      small
      @filtered="onFiltered"
    >
      <template v-slot:cell(ad_info)="row">
        <b-button variant="success" v-on:click="$router.push({name:'AdInfo', params:{id:row.item.id} } )">Info</b-button>
        <b-button v-show='roleToggle' variant="danger" v-on:click="deleteAd(row.item.id)">Delete</b-button>
      </template>
    </b-table>
    <b-button v-show='roleToggle' block variant="primary"  v-on:click="$router.push('AddAd')">Submit your own add</b-button>
  </div>
</template>

<script>
import AdService from '../services/ad-service'

export default {
  name: 'Ad',
  data () {
    return {
      msg: '',
      roleToggle: false,
      totalItems: '',
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15, { value: 100, text: 'Show a lot' }],
      filter: null,
      ads: [{
        id: '',
        title: '',
        description: '',
        company_name: ''
      }],
      fields: [
        { key: 'title', label: 'Position' },
        { key: 'description', label: 'Description' },
        { key: 'company_name', label: 'Employer' },
        { key: 'ad_info', label: 'Actions' }
      ],
      filters: {
        title: '',
        description: '',
        company_name: ''
      }
    }
  },
  computed: {
    sortOptions () {
      return this.fields
        .filter(f => f.sortable)
        .map(f => {
          return { text: f.label, value: f.key }
        })
    }
  },

  mounted () {
    this.getAllAds()
    this.totalRows = this.items.length
  },
  methods: {
    onFiltered (filteredItems) {
      this.totalRows = filteredItems.length
      this.currentPage = 1
    },
    getAllAds () {
      AdService.getAllAds().then(
        response => {
          console.log(response)
          this.ads = response.data
        },
        error => {
          this.content =
          (error.response && error.response.data)
          error.message ||
          error.toString()
        }
      )
    },
    deleteAd (id) {
      AdService.deleteAd(id).then(
        response => {
          console.log(response)
          this.message = response.data
          this.getAllAds()
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        }
      )
    }
  }
}

</script>

<style scoped>

</style>

import axios from 'axios'

const API_URL = 'http://localhost:8080/ad'

class AdService {
  getAllAds () {
    return axios.get(API_URL + '/all')
  }

  getAdById (id) {
    return axios.get(API_URL + '/id',
      {
        params:
          {
            id: id
          }
      })
  }

  deleteAd (id) {
    return axios.delete(API_URL + '/id',
      {
        params:
        {
          id: id
        }
      })
  }

  saveAdDB (form) {
    return axios.post(API_URL + '/save/ad',
      {
        title: form.title,
        description: form.description,
        company_name: form.company_name,
        id: form.id
      })
  }
}

export default new AdService()

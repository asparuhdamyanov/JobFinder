import axios from 'axios'

const API_URL = 'http://localhost:8080/users'

class UserService {
  getAllUsers () {
    return axios.get(API_URL + '/all')
  }

  getUserById (id) {
    return axios.get(API_URL + '/id',
      {
        params:
          {
            id: id
          }
      })
  }

  saveUser (form) {
    return axios.post(API_URL + '/save/user',
      {
        username: form.username,
        password: form.password,
        firstName: form.first_name,
        lastName: form.last_name,
        email: form.email,
        id: form.id
      })
  }
}

export default new UserService()

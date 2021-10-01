<template>
 <div id="app">
   <div class="container">
     <form>
       <div class="row">
         <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
           <h1>Signup</h1>
           <hr>
           <div class="form-group">
             <label for="firstName">First Name</label>
             <input type="text" id="firstName" class="form-control" v-model="form.firstName">
           </div>
           <div class="form-group">
             <label for="lastName">Last Name</label>
             <input type="text" id="lastName" class="form-control" v-model="form.lastName">
           </div>
           <div class="form-group">
             <label for="lastName">Username</label>
             <input type="text" id="username" class="form-control" v-model="form.username">
           </div>
           <div class="form-group">
             <label for="email">Mail</label>
             <input type="text" id="email" class="form-control" v-model="form.email">
           </div>
           <div class="form-group">
             <label for="password">Password</label>
             <input type="password" id="password" class="form-control" v-model="form.password">
             <p>
               {{ form.password }}
             </p>
           </div>
           <div class="form-check">
                <input class="form-check-input" id="no" v-model="form.isEmployee" v-bind:value="false" type="radio">
                <label class="form-check-label" for="no">
                  Employer role
                </label>
              </div>
           <div class="form-check">
                <input class="form-check-input" id="yes" v-model="form.isEmployee" v-bind:value="true" type="radio" checked>
                <label class="form-check-label" for="yes">
                  Employee role
                </label>
           </div>
         </div>
       </div>
       <hr>
       <div class="row">
         <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
           <button class="btn btn-primary" v-on:click="saveUser">Submit!
           </button>
         </div>
       </div>
     </form>
   </div>
 </div>
</template>

<script>
import UserService from '../services/user-service'

export default {
  name: 'User',
  data () {
    return {
      form: {
        id: '',
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        isEmployee: ''
      },
      isSubmitted: false
    }
  },
  methods: {
    saveUser () {
      UserService.saveUser(this.form).then(
        response => {
          console.log(response)
          this.form = response.data.user
          this.message = response.data.message
        },
        error => {
          error.message =
          error.data.message.toString() ||
          error.message ||
          (error.response && error.response.data)
        }
      )
    },
    submitted () {
      this.isSubmitted = true
    }
  }
}
</script>

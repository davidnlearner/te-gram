<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <br />
      <h4>Create Username</h4>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br />
      <h4>Create Password</h4>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br />
      <h4>Confirm Password</h4>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br /><br /><br />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <br /><br /><br />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#register {
  background-image: url("../Assets/background.png");
  background-size: cover;
  position: absolute;
  top: 95px;
  right: 0px;
  bottom: 0px;
  left: 0px;
}

.form-register {
  text-align: center;
}
#register button:hover {
  background-color: #00adee;
}

#register button {
  width: 120px;
  height: 30px;
  border-radius: 8px;
border-style: solid;
  border-width: thin;
  font-weight: bold;

}

.form-register {
  text-align: center;
  width: 300px;
  margin: 60px auto 30px;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  padding: 10px;
}
</style>

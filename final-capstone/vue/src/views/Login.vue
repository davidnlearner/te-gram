<template>
  <div id="login" class="text-center">
    <form class="form-signin" autocomplete="off" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <br />
      <h4>Username</h4>
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
      <h4>Password</h4>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br /><br /><br />
      <button type="submit">Sign in</button>

      <br /><br /><br />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  created() {
    if (this.$route.query.username && this.$route.query.password) {
      this.user.username = this.$route.query.username;
      this.user.password = this.$route.query.password;
      this.login();
    }
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
#login {
  background-image: url("../Assets/background.png");
  background-size: cover;
  position: absolute;
  top: 95px;
  right: 0px;
  bottom: 0px;
  left: 0px;
}

#login button:hover {
  background-color: #00adee;
}
#login button {
  width: 80px;
  height: 30px;
  border-radius: 8px;
  border-style: solid;
  border-width: thin;
  font-weight: bold;
}
.form-signin {
  text-align: center;
  width: 300px;
  margin: 60px auto 30px;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  padding: 10px;
}
</style>

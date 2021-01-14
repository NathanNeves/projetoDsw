<template lang="html">
  <div class="login row" v-if="!$root.credentials">
    <div class="inner-login col-md-4 col-md-offset-4 text-left">
      <h2 class="form-title">Login</h2>
      <h6 class="form-subtitle">Entre com as suas credenciais para o login.</h6>

      <form @submit.prevent="processForm">
        <div class="form-group">
          <label for="username">Login</label>
          <input
            type="text"
            class="form-control"
            id="username"
            placeholder="Entre o seu login"
            v-model="form.email"
          />
          <span class="error" v-if="error.all">{{ error.all }}</span>
        </div>

        <div class="form-group">
          <label for="password">Senha</label>
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="Entre e sua senha"
            v-model="form.senha"
          />
        </div>

        <button type="submit" class="btn btn-primary">Envia</button>
      </form>

      <div class="link-recuperar-senha">
        <router-link class="link" :to="{ name: 'forgot-password' }" replace>
          Recuperar a minha senha
        </router-link>
      </div>

      <div class="link-criar-conta">
        <router-link class="link" :to="{ name: 'create-account' }" replace>
          Criar nova conta
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: { email: "", senha: "" },
      error: {},
    };
  },

  methods: {
    processForm: function() {
      axios
        .post(this.$root.config.url + "/auth", this.form)
        .then((response) => {
          this.$root.credentials = response.data.data;
          sessionStorage.setItem("token", response.data.data);
          this.$router.replace("/");
          this.error = {};
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },
  },
};
</script>

<style lang="css" scoped>
div.login {
  width: 100vw;
  min-height: 100vh;
  background: #d5d2ff;
}

.inner-login {
  border: 5px #4d54d2;
  margin: 32px 20px;
}

div.link-recuperar-senha {
  margin-top: 32px;
}

div.link-criar-conta {
  margin-top: 8px;
}

input[type="text"],
input[type="password"] {
  background-color: #f6f6f6;
  border: none;
  color: #0d0d0d;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 5px;
  width: 85%;
  border: 2px solid #f6f6f6;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
  transition: all 0.5s ease-in-out;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
}
</style>

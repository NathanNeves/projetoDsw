<template lang="html">
  <div class="reinicializacao-senha row">
    <div class="inner-reinicializacao col-md-4 col-md-offset-4 text-left border">
      <h2 class="form-title">Reinicialização de senha</h2>
      <h6 class="form-subtitle">Entre com a nova senha no formulário abaixo.</h6>
      
      <form @submit.prevent="processForm">
        <div class="form-group">
          <label for="password">Digite a nova senha</label>
          <input type="password" class="form-control" id="password" placeholder="Entre com uma senha" v-model="form.senha"></input>
          <span class="error" v-if="error.senha">{{error.senha}}</span>
        </div>

        <div class="form-group">
          <label for="password-repeat">Repita a nova senha</label>
          <input type="password" class="form-control" id="password-repeat" placeholder="Repita a sua senha" v-model="form.senhaRepetida"></input>
          <span class="error" v-if="error.senhaRepetida">{{error.senhaRepetida}}</span>
        </div>

        <button type="submit" class="btn btn-primary">Troca Senha</button>
      </form>

      <div class="link-login">
        <router-link class="link" :to="{ name: 'login' }">
          Lembrei da minha senha e não quero mais trocar
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        form: { token: this.$route.query.token, email: this.$route.query.email, senha: "", senhaRepetida: "" },
        error: { }
      }
    },

    methods: {
      processForm: function() {
        axios.post(this.$root.config.url + "/api/usuario/reset", this.form)
          .then(response => {
            this.$router.replace('reseted');
            this.error = {};
          })
          .catch(error => {
            this.error = error.response.data.errors;
          });
      }
    }
  }
</script>

<style lang="css" scoped>

div.reinicializacao-senha {
  width: 100vw;
  min-height: 100vh;
}

.inner-reinicializacao{
  height: 100%;
  margin: 32px 30%;
  background: #e5e3ff;
}

.form-title, .form-group {
  color: #6d22c7;
}

.form-subtitle{
  color: #9467c9;
}

div.link-login {
  margin-top: 32px;
  margin-bottom: 5px;
}
input[type="text"],
input[type="password"] {
  background-color: #f6f6f6;
  border: none;
  color: #0d0d0d;
  text-decoration: none;
  display: block;
  font-size: 16px;
  display: inline-block;
  margin: 5px;
  width: 70%;
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
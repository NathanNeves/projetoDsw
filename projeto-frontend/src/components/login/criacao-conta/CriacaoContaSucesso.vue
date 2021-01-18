<template lang="html">
  <div class="conta-criada row">
    <div class="inner-criada text-left border">
      <h2 class="form-title">Conta registrada!</h2>
      <h6 class="form-subtitle">Faça o login para entrar no sistema.</h6>

      <div class="link-login">
        <router-link class="link" :to="{ name: 'login' }">
          Ir para a página de login.
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="css" scoped>
div.conta-criada {
  height: 100%;
  margin: 32px 30%;
  background: #e5e3ff;
}

.inner-criada {
  margin: 32px 20px;
}

div.link-login {
  margin-top: 32px;
}

.form-title {
  color: #6d22c7;
}

.form-subtitle {
  color: #9467c9;
}
</style>

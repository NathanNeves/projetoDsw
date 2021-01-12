<template lang="html">
  <div class="token-enviado row">
    <div class="inner-token col-md-4 col-md-offset-4 text-left">
      <h2 class="form-title">Token enviado!</h2>
      <h6 class="form-subtitle">
        Um token para recuperação de senha foi enviado para o seu e-mail. Clique
        no link deste e-mail para entrar com uma nova senha.
      </h6>

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
div.token-enviado {
  width: 100vw;
  min-height: 100vh;
  background: #d5d2ff;
}
.inner-token {
  margin: 32px 20px;
}
div.link-login {
  margin-top: 32px;
}
</style>

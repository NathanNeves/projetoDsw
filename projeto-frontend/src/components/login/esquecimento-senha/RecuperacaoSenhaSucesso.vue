<template lang="html">
  <div class="senha-reinicializada row">
    <div class="inner-reinicializada col-md-4 col-md-offset-4 text-left">
      <h2 class="form-title">Senha trocada!</h2>
      <h6 class="form-subtitle">
        A sua senha foi trocada. Você pode utilizar esta nova senha para acessar
        o sistema.
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
div.senha-reinicializada {
  width: 100vw;
  min-height: 100vh;
  background: #d5d2ff;
}
.inner-reinicializada {
  margin: 32px 20px;
}
div.link-login {
  margin-top: 32px;
}
</style>

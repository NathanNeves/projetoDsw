<template lang="html">
  <div class="senha-reinicializada row">
    <div class="inner-reinicializada text-left border">
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
  height: 100%;
  margin: 32px 30%;
  background: #e5e3ff;
}
.inner-reinicializada {
  margin: 32px 20px;
}
.form-subtitle {
  text-align: center;
}
div.link-login {
  margin-top: 32px;
}
</style>

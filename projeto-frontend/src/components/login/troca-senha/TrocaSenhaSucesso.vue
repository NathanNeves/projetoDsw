<template lang="html">
  <div class="senha-trocada row">
    <div class="inner-trocada col-md-4 col-md-offset-4 text-left">
      <h2 class="form-title">Senha trocada!</h2>
      <h6 class="form-subtitle">
        A sua senha foi trocada. Você deve utilizar esta nova senha para acessar
        o sistema nas próximas vezes.
      </h6>

      <div class="link-home">
        <router-link class="link" :to="{ name: 'home' }">
          Ir para a homepage.
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="css" scoped>
.html {
  background: #e5e3ff;
}
div.senha-trocada {
  width: 100vw;
  min-height: 100vh;
}
.inner-trocada {
  margin: 32px 20px;
}
div.link-home {
  margin-top: 32px;
}
</style>

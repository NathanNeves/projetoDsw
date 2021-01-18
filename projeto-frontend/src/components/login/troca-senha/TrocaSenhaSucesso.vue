<template lang="html">
  <div class="senha-trocada row">
    <div class="inner-trocada border">
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
div.senha-trocada {
  height: 100%;
  margin: 32px 30%;
  background: #e5e3ff;
}
div.link-home {
  margin-top: 32px;
}
.form-title {
  color: #6d22c7;
}
.form-subtitle {
  width: 100%;
  color: #9467c9;
}
</style>

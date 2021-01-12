<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light border-bottom">
      <div class="container-fluid">
        <router-link to="/" class="logo navbar-brand">Shared4U</router-link>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <ul class="navbar-nav ms-auto">
            <li class="nav-link">
              <router-link to="/" class="nav-link">Home</router-link>
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/item/list" class="nav-link"
                >Compartilhamento</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login" class="nav-link">Login</router-link>
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/new" class="nav-link">Create</router-link>
            </li>
            <span class="border-right"></span>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/account-created" class="nav-link">
                Conta criada
              </router-link>
            </li>

            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/forgot" class="nav-link"
                >Senha esquecida</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/token-sent" class="nav-link"
                >Token enviado</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/reset" class="nav-link"
                >Resetar senha</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/reseted" class="nav-link"
                >Senha resetada</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/change" class="nav-link"
                >Modificar senha</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/login/changed" class="nav-link"
                >Senha modificada</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/item/update" class="nav-link"
                >Atualizar itens</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/item/delete" class="nav-link"
                >Novo item</router-link
              >
            </li>
            <span class="border-right"></span>
            <li class="nav-link">
              <router-link to="/item/new" class="nav-link"
                >Deletar item</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  methods: {
    logout: function() {
      this.$root.credentials = null;
      this.$router.replace("/");
    },
  },
};
</script>

<style>
li {
  margin: auto;
}
li.login-commands {
  color: black;
  padding-top: 15px;
  margin-right: 32px;
}
li.nav-link:hover {
  background-color: #fff0f2;
}
.logo {
  margin-left: 18px;
}
.navbar {
  height: 6vh;
}
li.nav-link {
  border-left: 1px solid #eee;
  border-right: 1px solid #eee;
}
li.user-commands {
  color: black;
  padding-top: 15px;
  margin-right: 32px;
}

li.user-commands span.greetings {
  margin-right: 12px;
}
</style>

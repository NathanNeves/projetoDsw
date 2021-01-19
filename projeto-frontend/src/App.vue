<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="container-fluid border-bottom">
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
            <li class="nav-link" v-if="this.$root.credentials">
              <router-link to="/item/list" class="nav-link"
                >Compartilhamento</router-link
              >
            </li>
            <li class="nav-link" v-if="this.$root.credentials">
              <router-link to="/login/change" class="nav-link"
                >Trocar senha</router-link
              >
            </li>
            <li class="nav-link" v-if="this.$root.credentials">
                 <div class="convites dropdown">
                    <i class="fas fa-bell mt-2" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false"></i>
                    <ul class="dropdown-menu dropstart" v-if="this.convites.length > 0" aria-labelledby="dropdownMenuButton">
                      <li v-for="convite in this.convites" :key="convite.id">
                        <a
                          class="dropdown-item"
                          href="#"
                          @click:
                          role="button"
                          v-if="aceito == false && rejeitado == false"
                          >{{ convite }}</a
                        >
                      </li>
                    </ul>
                  </div>
            </li>


            <li class="nav-link" v-if="!this.$root.credentials">
              <router-link to="/login" class="nav-link">Login</router-link>
            </li>
            <li class="nav-link" v-else @click="logout">
              <router-link class="nav-link" to="/">Logout</router-link>
            </li>
            <li class="nav-link" v-if="!this.$root.credentials">
              <router-link to="/login/new" class="nav-link">Create</router-link>
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
    return {
      componentKey: 0,
      convites:[]
    };
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
  display: inline;
  
}
li.nav-link {
  height: 6vh;

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

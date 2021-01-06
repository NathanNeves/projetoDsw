<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light border-bottom">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Shared4U</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse  navbar-collapse" id="navbarNavAltMarkup">
          <ul class="navbar-nav ms-auto">
            <li class='nav-link'>
                <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <span class="border-right"></span>
            <li class='nav-link'>
                <a class="nav-link" href="#">Features</a>
            </li>
            <span class="border-right"></span>
            <li class='nav-link'>
                <a class="nav-link" href="#">Pricing</a>
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
    data () {
      return { }
    },

    methods: {
      logout: function() {
        this.$root.credentials = null;
        this.$router.replace('/');
      }
    }
  }
</script>

<style>
span.greetings {
  color: black;
  margin: 0 4px;
}
li {
  margin: 0 4px;
}
li.login-commands {
  color: black;
  padding-top: 15px;
  margin-right: 32px;
}
li.login-commands a {
  display: inline;
  padding: 15px 8px;
}
li.user-commands {
  color: black;
  padding-top: 15px;
  margin-right: 32px;
}
li.user-commands span.greetings {
  margin-right: 12px;
}
li.user-commands a {
  display: inline;
  padding: 15px 8px;
}
</style>

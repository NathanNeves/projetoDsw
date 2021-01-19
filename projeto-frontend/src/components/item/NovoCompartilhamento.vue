<template lang="html">
  <div class="detalhes-items-compartilhado row">
    <div class="inner-detalhes col-md-8 col-md-offset-1 text-left">
      <h5>Nome do item: {{ item.nome }}</h5>
      <div class="input-box">
        Compartilhar com:
        <input type="email" @keyup.enter="update" />
      </div>
      <div class="input-box">
        Data de inicio:
        <input type="email" @keyup.enter="update" />
      </div>
      <div class="input-box">
        Data de término:
        <input type="email" @keyup.enter="update" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["item"],
  compartilhamento: {
    email: "",
    idItem: null,
    dtInicio: null,
    dtFim: null,
  },

  created: function() {
    this.getItem();
  },

  methods: {
    getItem: function() {
      axios
        .get(`/api/item/${item.id}`, this.httpOptions)
        .then((response) => {
          this.item = response.data.data.data;
          this.page = response.data.data.current_page;
          this.totalPages = response.data.data.last_page;
          this.error = {};
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    getCompartilhamentos: function(event) {
      compartilhamento.email = event.target.value;
      axios
        .post(`/api/compartilhamento/novo`, this.httpOptions)
        .then((response) => {
          this.compartilhamentos = response.data.data.data;
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },
  },
};
</script>

<style scoped>
.detalhes-items-compartilhado {
  margin: 32px 30%;
  background: #e5e3ff;
}
.inner-detalhes {
  margin: 5px 20px;
}
.card {
  margin: auto;
}
.text {
  color: #9467c9;
}
.input-box {
  text-align: center;
}
h5 {
  text-align: center;
  margin: 5px;
}
</style>

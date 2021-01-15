<template lang="html">
  <div class="detalhes-items-compartilhado row">
    <div class="inner-detalhes col-md-10 col-md-offset-1 text-left">
      <div class="header col">
        <div class="row">
          <h2 class="form-title text">Detalhes do item</h2>
        </div>
        <div class="details row">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Tipo</th>
                <th>Data de início</th>
                <th>Data de término</th>
                <th>Usuário</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{ item.nome }}</td>
                <td>{{ item.descricao }}</td>
                <td>{{ item.tipo }}</td>
                <td>Data vai aqui</td>
                <td>Data vai aqui</td>
                <td>Usuário vai aqui</td>
                <td>Status vai aqui</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["item"],

  created: function() {
    this.processForm();
  },

  methods: {
    processForm: function() {
      this.item = axios
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
  },
};
</script>

<style scoped>
.detalhes-items-compartilhado {
  width: 100vw;
  min-height: 100vh;
  background: #e5e3ff;
}
.inner-detalhes {
  margin: 5px 20px;
}
h2 {
  margin: 0;
  padding: 0;
  text-align: center;
}
div.details {
  margin: auto;
  width: 90%;
  height: 90%;
}
div.header.col {
  margin: auto;
}
</style>

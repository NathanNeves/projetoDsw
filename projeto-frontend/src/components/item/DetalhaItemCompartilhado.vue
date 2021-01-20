<template lang="html">
  <div class="detalhes-items-compartilhado row">
    <div class="inner-detalhes text-left">
      <div class="header col">
        <div class="row">
          <h2 class="form-title text">Detalhes do item</h2>
        </div>
        <table class='table mt-5'>
          <tbody>
            <tr>
              <td class='align-right'>
                <h5>Item: </h5>
              </td>
              <td>
                {{ item.nome }}
              </td>
            </tr>
            <tr>
              <td class='align-right'> 
                  <h5>Descrição:</h5>
              </td>
              <td>
                  {{ item.descricao }}
              </td>
            </tr>
            <tr>
              <td class='align-right'>
                  <h5>Tipo do Item:</h5>
              </td>
              <td>
                  {{ item.tipo }}
              </td>
            </tr>
          </tbody>
        </table>
        <div class="card text-center" v-if="this.compartilhamentos > 0" style="width: 30rem;">
          <div
            class="compartilhamentos"
            v-for="compartilhamento in compartilhamentos"
          >
            <table class="table">
              <tbody>
                <tr>
                  <th>Data de ínicio</th>
                  <td>{{ compartilhamento.inicio }}</td>
                </tr>
                <tr>
                  <th>Data de término</th>
                  <td>{{ compartilhamento.fim }}</td>
                </tr>
                <tr>
                  <th>Usuário</th>
                  <td>{{ compartilhamento.usuario }}</td>
                </tr>
                <tr>
                  <th>Status</th>
                  <td>{{ compartilhamento.status }}</td>
                </tr>
              </tbody>
            </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["item"],
  compartilhamentos: [],

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
    getCompartilhamentos: function() {
      axios
        .get(`/api/compartilhamento/lista?page=${item.page}&per_page=10`, this.httpOptions)
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
.card {
  margin: auto;
}
.text {
  color: #9467c9;
}
.detalhes-items-compartilhado {
  margin: 32px 20%;
}
.inner-detalhes {
  margin: 5px 20px;
}
h5 {
  text-align: center;
  margin: 5px;
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

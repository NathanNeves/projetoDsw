<template lang="html">
  <div
    class="form-items-compartilhados border"
    v-if="this.$root.credentials"
  >
    <div class="inner-form col-md-10 col-md-offset-1 text-left m-4">
      <h2 class="form-title">Novo item compartilhado</h2>
      <h6 class="form-subtitle">
        Entre com os dados do novo item compartilhado.
      </h6>

      <div class="success" v-if="success">
        Os dados do item foram salvos.
      </div>

      <form @submit.prevent="processForm">
        <div class="form-group">
          <label for="nome">Nome:</label>
          <input
            type="text"
            class="form-control"
            id="nome"
            placeholder="Entre o nome do item"
            v-model="item.nome"
          />
          <span class="error" v-if="error.nome">{{ error.nome }}</span>
        </div>

        <div class="form-group">
          <label for="descricao">Descrição:</label>
          <textarea
            rows="3"
            cols="80"
            class="form-control"
            id="descricao"
            placeholder="Descreva o item"
            v-model="item.descricao"
          />
          <span class="error" v-if="error.descricao">{{
            error.descricao
          }}</span>
        </div>

        <div class="form-group">
          <label for="tipo">Tipo:</label>
          <select class="form-control" id="tipo" v-model="item.tipo">
            <option value="">Selecione o tipo do item</option>
            <option value="UNICO">Item único</option>
            <option value="MULTIPLO">Item múltiplo</option>
          </select>
          <span class="error" v-if="error.tipo">{{ error.tipo }}</span>
        </div>

        <button type="submit" class="btn btn-primary mt-3">Enviar</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      item: { nome: "", descricao: "", tipo: "" },

      error: {},

      success: false,

      httpOptions: {
        baseURL: this.$root.config.url,
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + JSON.parse(sessionStorage.getItem('token')).token,
        },
      },
    };
  },

  methods: {
    processForm: function() {
      axios
        .post("/api/item/novo", this.item, this.httpOptions)
        .then((response) => {
          this.success = true;
          this.error = {};
          setTimeout(this.goBackToList, 2000);
        })
        .catch((error) => {
          if (error != undefined) {
            this.error = error.response.data.errors;
          }
        });
    },

    goBackToList: function() {
      this.$router.replace("/item/list");
    },
  },
};
</script>

<style lang="css" scoped>
div.form-items-compartilhados {
    width: 94.6vw;
    min-height: 80vh;
    margin-left:2.5%;
    margin-top:2%;
    background-color:white;
}
.inner-form {
  margin-top: 32px 20px;
}
div.success {
  border: 1px solid green;
  background: lightgreen;
  padding: 8px;
  margin-bottom: 24px;
}
.form-title,
.form-group {
  color: #6d22c7;
}

.form-subtitle {
  color: #9467c9;
}
</style>

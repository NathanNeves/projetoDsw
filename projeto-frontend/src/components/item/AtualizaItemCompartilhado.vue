<template lang="html">
  <div class="form-items-compartilhados  border" v-if="this.$root.credentials">
    <div class="inner-atualiza text-left">
      <h2 class="form-title">Edição de item compartilhado</h2>
      <h6 class="form-subtitle">Entre com os dados do item compartilhado.</h6>

      <div class="success" v-if="success">
        Os dados do item foram atualizados.
      </div>

      <form @submit.prevent="processForm">
        <div class="form-group">
          <label for="nome">Nome</label>
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
          <label for="descricao">Descrição</label>
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
          <label for="tipo">Tipo</label>
          <select class="form-control" id="tipo" v-model="item.tipo">
            <option value="">Selecione o tipo do item</option>
            <option value="UNICO">Item único</option>
            <option value="MULTIPLO">Item múltiplo</option>
          </select>
          <span class="error" v-if="error.tipo">{{ error.tipo }}</span>
        </div>

        <button type="submit" style="margin-top:10px" class="btn btn-primary">Enviar</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["item"],

  data() {
    return {
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
        .post("/api/item/atualiza", this.item, this.httpOptions)
        .then((response) => {
          this.success = true;
          this.error = {};
          setTimeout(this.goBackToList, 2000);
        })
        .catch((error) => {
          this.error = error.response.data.errors;
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
}
.inner-atualiza {
  height: 100%;
  margin: 32px 2%;
}
div.success {
  border: 1px solid green;
  background: lightgreen;
  padding: 8px;
  margin-bottom: 24px;
}
.form-title {
  color: #6d22c7;
}

.form-subtitle {
  color: #9467c9;
}
</style>

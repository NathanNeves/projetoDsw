<template lang="html">
  <div class="detalhes-items-compartilhad d-flex align-items-center justify-content-center">
    <div class="inner-detalhes col-md-8 col-md-offset-1 text-left">
      <h5>Nome do item: {{ item.nome }}</h5>
      <div class="form-group">
        Compartilhar com:
        <input v-model="this.email" class="form-control" type="email" />
      </div>
      <div class="form-group">
        Data de inicio:
        <input v-model="this.dtInicio" class="form-control" type="date" />
      </div>
      <div class="form-group">
        Data de término:
        <input v-model="this.dtFim" class="form-control" type="date" />
      </div>
      <div class="form-group">
        <button v-on:click="sendCompartilhamento" class="btn btn-primary mt-3 mb-3">Salvar</button>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import axios from 'axios';
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
          this.page = 1;
          if(response.data.data.current_page){
            this.page = response.data.data.current_page;
          }
          this.totalPages = response.data.data.last_page;
          this.error = {};
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    sendCompartilhamento: function() {
      axios
        .post(`/api/compartilhamento/novo`,{
          email:this.email,
          idItem:this.item.id,
          dtInicio:moment(this.dtInicio,'DD/MM/YYYY').format('X'),
          dtFim:moment(this.dtFim,'DD/MM/YYYY').format('X')
        }, this.httpOptions)
        .then((response) => {
              console.log('sucesso');
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
  height: 100%;
  width: 98%;
}
.inner-detalhes {
  width:70%;
  background: #e5e3ff;
  margin:200px;
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

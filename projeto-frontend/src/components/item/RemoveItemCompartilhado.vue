<template lang="html">
  <div class="remove-items-compartilhados row border" v-if="this.$root.credentials">
    <div class="inner-remove col-md-10 col-md-offset-1 text-left">
      <h2 class="form-title">Remoção de item compartilhado</h2>
      <h6 class="form-subtitle">Confirme a remoção do item compartilhado.</h6>

      <div class="success" v-if="success">
        O item foi removido do sistema.
      </div>

      <div class="error" v-if="error">
        Ocorreu um erro ao remover o item do sistema.
      </div>

      <div>
        <p class="label">Nome</label>
        <p class="text" >{{this.item.nome}}</p>

        <p class="label">Descrição</label>
        <p class="text" >{{this.item.descricao}}</p>

        <p class="label">Tipo</label>
        <p class="text" >{{this.item.tipo}}</p>

        <button type="submit" class="btn btn-danger" @click="remove">Remover o item</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
//import func from '../../../vue-temp/vue-editor-bridge';

export default {
  props: ['idItem'],

  data() {
    return {
      error: false,
      success: false,
      item:{},
      httpOptions: {
          baseURL: this.$root.config.url,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + JSON.parse(sessionStorage.getItem('token')).token
          }
      },
    }
  },

  created:function(){
     this.loadItem();
  },

  methods: {
    remove: function() {
      axios.delete("/api/item/" + this.idItem, this.httpOptions)
        .then(response => {
          this.success = true;
          this.error = false;
          setTimeout(this.goBackToList, 2000);
        })
        .catch(error => {
          this.error = true;
          this.success = false;
        });
    },

    goBackToList: function() {
      this.$router.replace('/item/list');
    },

    loadItem: async function(){
      try{
          let response = await axios.get('/api/item/'+this.idItem,this.httpOptions);
          console.log('resultado:',response.data);
          this.item = response.data.data;
      }catch(e){
            this.error = true;
      }
    }

  }
}
</script>

<style lang="css" scoped>
div.remove-items-compartilhados {
  height: 100%;
  margin: 32px 2%;
}
.inner-remove {
  margin: 32px 20px;
}
div.success {
  border: 1px solid green;
  background: lightgreen;
  padding: 8px;
  margin-bottom: 24px;
}
div.error {
  border: 1px solid red;
  background: lightcoral;
  padding: 8px;
  margin-bottom: 24px;
}
p.label {
  color: black;
  font-weight: bold;
  text-align: left;
  display: block;
  font-size: 100%;
  padding: 0px 0px 0px 0px;
  margin-bottom: 4px;
}
p.text {
  margin-bottom: 32px;
}
.form-title {
  color: #6d22c7;
}
.form-subtitle {
  color: #9467c9;
}
</style>
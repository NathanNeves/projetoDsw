<template lang="html">
  <div class="lista-items-compartilhados border">
    <div class="inner-lista col-md-10 col-md-offset-1 text-left ">
      <div>
        <div class="header">
          <h2 class="form-title text">Itens Compartilhados</h2>
          <h6 class="form-subtitle text">
            Abaixo estão os seus itens compartilhados.
          </h6>
        </div>
        <div class="clear"></div>
      </div>
      <input type="text" class="form-control" @input="filtra" />
      <div class="text">
        <input type="checkbox" @click="ativarNome" />
        <label>Nome</label>
        <input type="checkbox" @click="ativarDescricao" />
        <label>Descrição</label>
      </div>
      <div class="new-button">
        <button type="button" class="btn btn-success" @click="novo">
          <i class="fas fa-plus"></i>
          Novo Item
        </button>
      </div>
      <table class="table" id="tbItens">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Tipo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items">
            <td>{{ item.nome }}</td>
            <td>{{ item.descricao }}</td>
            <td>{{ item.tipo }}</td>
            <td class="icons row">
              <button class="btn col" @click="detalha(item)">
                <i class="fa fa-eye" aria-hidden="true"></i>
              </button>
              <button class="btn col">
                <i
                  class="fa fa-share"
                  aria-hidden="true"
                  @click="compartilha(item)"
                ></i>
              </button>
              <button class="btn col">
                <i
                  class="fa fa-edit"
                  aria-hidden="true"
                  @click="edita(item)"
                ></i>
              </button>
              <button class="btn col">
                <i
                  class="fa fa-trash-alt"
                  aria-hidden="true"
                  @click="remove(item)"
                ></i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <div>
        <div
          class="page-item first"
          :class="{ disable: this.page == 1 }"
          @click="moveTo(page - 1)"
        >
          &lt;&lt;
        </div>
        <div class="page-item" v-show="page > 3" @click="moveTo(page - 3)">
          {{ page - 3 }}
        </div>
        <div class="page-item" v-show="page > 2" @click="moveTo(page - 2)">
          {{ page - 2 }}
        </div>
        <div class="page-item" v-show="page > 1" @click="moveTo(page - 1)">
          {{ page - 1 }}
        </div>
        <div class="page-item current disable">{{ page }}</div>
        <div
          class="page-item"
          v-show="totalPages > page"
          @click="moveTo(page + 1)"
        >
          {{ page + 1 }}
        </div>
        <div
          class="page-item"
          v-show="totalPages > page + 1"
          @click="moveTo(page + 2)"
        >
          {{ page + 2 }}
        </div>
        <div
          class="page-item"
          v-show="totalPages > page + 2"
          @click="moveTo(page + 3)"
        >
          {{ page + 3 }}
        </div>
        <div
          class="page-item last"
          :class="{ disable: this.page == this.totalPages }"
          @click="moveTo(page + 1)"
        >
          &gt;&gt;
        </div>
        <div class="clear"></div>
      </div>
     
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      page: 1,
      totalPages: 1,
      items: [],
      convites: [],
      busca: "",
      descricao: false,
      nome: false,
      filter: 0,
      perPage: 10,

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

  created: function() {
    this.processForm();
    this.getConvites();
  },

  methods: {
    processForm: function() {
      axios
        .get(
          `/api/item/lista?page=${this.page}&per_page=${this.perPage}&texto=${this.busca}&descricao=${this.descricao}&nome=${this.nome}`,
          this.httpOptions
        )
        .then((response) => {
          this.items = response.data.data.data;
          if (response.data.data.current_page) {
            this.page = response.data.data.current_page;
          }
          this.totalPages = response.data.data.last_page;
          this.error = {};
          this.subItems = this.items;
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    getConvites: function() {
      axios
        .get(`/api/compartilhamento/userAlert`, this.httpOptions)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    moveTo: function(page) {
      if (page <= 1) page = 1;

      if (page > this.totalPages) page = this.totalPages;

      this.page = page;
      this.processForm();
    },

    compartilha: function(item) {
      this.$router.push({
        name: "item-share",
        params: { item: item },
      });
    },

    novo: function() {
      this.$router.push({ name: "item-new" });
    },

    edita: function(item) {
      this.$router.push({
        name: "item-update",
        params: { item: item },
      });
    },

    detalha: function(item) {
      this.$router.push({
        name: "item-details",
        params: { item: item },
      });
    },

    filtra: function(event) {
      this.busca = event.target.value;
      this.page = 1;
      axios
        .get(
          `/api/item/lista?page=${this.page}&per_page=10&texto=${this.busca}&descricao=${this.descricao}&nome=${this.nome}`,
          this.httpOptions
        )
        .then((response) => {
          this.items = response.data.data.data;
          if (response.data.data.current_page) {
            console.log("=============");
            this.page = response.data.data.current_page;
          }
          this.totalPages = response.data.data.last_page;
          this.error = {};
          this.subItems = this.items;
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    remove: function(item) {
      this.$router.push({
        path:`/item/delete/${item.id}`
      })},

    ativarNome: function() {
      this.nome = !this.nome;
      this.page = 1;
      axios
        .get(
          `/api/item/lista?page=${this.page}&per_page=10&texto=${this.busca}&descricao=${this.descricao}&nome=${this.nome}`,
          this.httpOptions
        )
        .then((response) => {
          this.items = response.data.data.data;
          if (response.data.data.current_page) {
            console.log("=============");
            this.page = response.data.data.current_page;
          }
          this.totalPages = response.data.data.last_page;
          this.error = {};
          this.subItems = this.items;
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    ativarDescricao: function() {
      this.descricao = !this.descricao;
      this.page = 1;
      axios
        .get(
          `/api/item/lista?page=${this.page}&per_page=10&texto=${this.busca}&descricao=${this.descricao}&nome=${this.nome}`,
          this.httpOptions
        )
        .then((response) => {
          this.items = response.data.data.data;
          if (response.data.data.current_page) {
              this.page = response.data.data.current_page;
          }
          this.totalPages = response.data.data.last_page;
          this.error = {};
          this.subItems = this.items;
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },
  },
};
</script>

<style lang="css" scoped>
.lista-items-compartilhados {
  width: 94.6vw;
  min-height: 80vh;
  margin-left:2.5%;
  margin-top:2%;
  background-color: white;
}

.form-control{
    width:70%;
}

.inner-lista {
  height: 100%;
  width: 96%;
  margin: 2%;
}
h6.form-subtitle.text {
  color: #9467c9;
}
button.btn.col {
  display: inline;
  width: 0px;
}
.text,
h2,
thead,
.td {
  color: #6d22c7;
}
td.icons.row {
  padding: 0px;
}
.th {
  color: #9467c9;
}
div.page-item {
  color: #9467c9;
  text-decoration: none;
  cursor: pointer;
  padding: 10px;
  border: 1px solid lightgray;
  margin: 0px -1px 0px 0px;
  float: left;
}

div.page-item.first {
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
}

div.page-item.last {
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
}

div.page-item.disable {
  cursor: auto;
}

div.page-item.current {
  background-color: lightgray;
}

div.clear {
  clear: both;
}

div.header {
  float: left;
}

div.new-button {
  float: right;
}
.convites {
  margin: 10px;
}
</style>

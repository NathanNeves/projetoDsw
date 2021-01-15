<template lang="html">
  <div class="lista-items-compartilhados row">
    <div class="inner-lista col-md-10 col-md-offset-1 text-left">
      <div>
        <div class="header">
          <h2 class="form-title text">Itens Compartilhados</h2>
          <h6 class="form-subtitle text">
            Abaixo estão os seus itens compartilhados.
          </h6>
        </div>
        <div class="new-button">
          <button type="button" class="btn btn-primary" @click="novo">
            Novo Item
          </button>
        </div>
        <div class="clear"></div>
      </div>
      <input type="text" class="search-bar" />
      <table class="table table-striped" id="tbItens">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Tipo</th>
            <th class="commands"></th>
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
      busca: "",
      descricao: false,
      nome: false,

      httpOptions: {
        baseURL: this.$root.config.url,
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.$root.credentials.token,
        },
      },
    };
  },

  created: function() {
    this.processForm();
  },

  methods: {
    processForm: function() {
      axios
        .get(
          `/api/item/lista?page=${this.page}&per_page=10&texto=${this.busca}&descricao=${this.descricao}&nome=${this.nome}`,
          this.httpOptions
        )
        .then((response) => {
          this.items = response.data.data.data;
          this.page = response.data.data.current_page;
          this.totalPages = response.data.data.last_page;
          this.error = {};
        })
        .catch((error) => {
          this.error = error.response.data.errors;
        });
    },

    moveTo: function(page) {
      if (page < 1) page = 1;

      if (page > this.totalPages) page = this.totalPages;

      this.page = page;
      this.processForm();
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

    remove: function(item) {
      this.$router.push({
        name: "item-delete",
        params: { item: item },
      });
    },
  },
};
</script>

<style lang="css" scoped>
.lista-items-compartilhados {
  width: 100vw;
  min-height: 100vh;
  background: #e5e3ff;
}

.inner-lista {
  margin: 5px 20px;
}

button.btn.col {
  display: inline;
  width: 0px;
}

th.commands {
  width: 60px;
}

.icons {
  width: 110px;
}

td.icons.row {
  padding: 0px;
}

div.page-item {
  color: #2973b7;
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
  color: gray;
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
  text-align: right;
}
</style>

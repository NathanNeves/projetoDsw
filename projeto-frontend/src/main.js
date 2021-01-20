import Vue from 'vue'
import Router from 'vue-router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { faFontAwesome } from '@fortawesome/free-brands-svg-icons'
//import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import App from './App.vue'
import Homepage from './components/Homepage.vue'
import Login from './components/login/login/Login.vue'

/* Criacao de conta */
import CriacaoConta from './components/login/criacao-conta/CriacaoConta.vue'
import CriacaoContaSucesso from './components/login/criacao-conta/CriacaoContaSucesso.vue'

/* Esquecimento de senha */
import EsquecimentoSenha from './components/login/esquecimento-senha/EsquecimentoSenha.vue'
import EsquecimentoSenhaSucesso from './components/login/esquecimento-senha/EsquecimentoSenhaSucesso.vue'
import RecuperacaoSenha from './components/login/esquecimento-senha/RecuperacaoSenha.vue'
import RecuperacaoSenhaSucesso from './components/login/esquecimento-senha/RecuperacaoSenhaSucesso.vue'

/* Troca de senha */
import TrocaSenha from './components/login/troca-senha/TrocaSenha.vue'
import TrocaSenhaSucesso from './components/login/troca-senha/TrocaSenhaSucesso.vue'

/* Itens compartilhados */
import ListaItemCompartilhado from './components/item/ListaItemCompartilhado.vue'
import NovoItemCompartilhado from './components/item/NovoItemCompartilhado.vue'
import AtualizaItemCompartilhado from './components/item/AtualizaItemCompartilhado.vue'
import RemoveItemCompartilhado from './components/item/RemoveItemCompartilhado.vue'
import DetalhaItemCompartilhado from './components/item/DetalhaItemCompartilhado.vue'

/* Compartilhamentos */
import NovoCompartilhamento from './components/item/NovoCompartilhamento.vue'

library.add(faUserSecret)
library.add(faFontAwesome)

//Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
   {
     path: '/',
     name: 'home',
     component: Homepage
   },
   {
     path: '/login',
     name: 'login',
     component: Login,
   },
   {
    path: '/login/new',
    name: 'create-account',
    component: CriacaoConta,
  },
  {
   path: '/login/account-created',
   name: 'account-created',
   component: CriacaoContaSucesso,
  },
  {
    path: '/login/forgot',
    name: 'forgot-password',
    component: EsquecimentoSenha,
  },
  {
    path: '/login/token-sent',
    name: 'token-sent',
    component: EsquecimentoSenhaSucesso,
  },  
  {
    path: '/login/reset',
    name: 'reset-password',
    component: RecuperacaoSenha,
  },
  {
    path: '/login/reseted',
    name: 'password-reseted',
    component: RecuperacaoSenhaSucesso,
  },  
  {
    path: '/login/change',
    name: 'change-password',
    component: TrocaSenha,
  },
  {
    path: '/login/changed',
    name: 'password-changed',
    component: TrocaSenhaSucesso,
  },
  {
    path: '/item/list',
    name: 'item-list',
    component: ListaItemCompartilhado,
  },
  {
    path: '/item/update',
    name: 'item-update',
    component: AtualizaItemCompartilhado,
    props: true
  },
  {
    path: '/item/new',
    name: 'item-new',
    component: NovoItemCompartilhado,
  },
  {
    path: '/item/delete/:idItem',
    name: 'item-delete',
    component: RemoveItemCompartilhado,
    props: true
  },
  {
    path: '/item/details',
    name: 'item-details',
    component: DetalhaItemCompartilhado,
    props: true
  },
  {
    path: '/item/share',
    name: 'item-share',
    component: NovoCompartilhamento,
    props: true
  }
]
})

new Vue({
  data: {
    credentials: null,
    config: {
      url: "http://localhost:8080"
    }
  },
  
  mounted(){
    let json = sessionStorage.getItem('token');
    if(json){
        this.credentials = JSON.parse(json);
    }
  },

 el: '#app',
 render: h => h(App),
 router
})
<template lang="html">
  <div>
    <div  class='container-fluid main'>
        <div class='text-container'>
          <h1 class='principal'>Compartilhe seus arquivos com aqueles que estão <p>mais distantes de você!</p></h1>
        </div>
        <button type="button" class="btn btn-outline-light btn-main btn-lg">Compartilhar!</button>

    </div>




    <div style="display:none" class="login">
      <div class="row jumbotron-row">
        <div class="col-md-offset-1 col-md-10">
          <div class="jumbotron text-center">
            <h1>Jumbotron heading</h1>
            <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>

            <p v-if="!$root.credentials">
              <router-link class="link" :to="{ name: 'login' }">
                <a class="btn btn-lg btn-success" href="#" role="button">Login</a>
              </router-link>
            </p>
          </div>
        </div>
      </div>
  
      <div class="row marketing">
        <div class="col-md-offset-1 col-md-4">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum. Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. </p>
        </div>

        <div class="col-md-offset-2 col-md-4">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum. Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. Maecenas sed diam eget risus varius blandit sit amet non magna, consectetur ac, vestibulum at eros. </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
  }
</script>

<style lang="css" scoped>


.principal{
    font-size:2.5rem;
    color:white;
    font-family: "BC Alphapipe L", sans-serif;
    margin-top:4%;
}

.btn-main{
  width:15vw;
  height: 4vw;
}

.text-container{
  width:100%;
  height: 90%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.main{
    background:rgb(105,99,213);
    background-color: linear-gradient(38deg, rgba(105,99,213,1) 8%, rgba(108,99,255,1) 47%, rgba(91,81,245,1) 100%);
    height:100vh;
    width:100%;
    text-align:center;
    background-image: url('../assets/wallpaper2.svg');
    background-size: 90vh;
    background-position-x:50%;
    background-position-y:20vh;
    background-repeat: no-repeat;
}



div.jumbotron-row {
  margin-top: 32px;
}
.marketing h4 {
  font-size: 20px;
}
.footer {
    margin-top: 64px;
    margin-bottom: 32px;
    padding-top: 8px;
    color: #777;
    border-top: 1px solid #e5e5e5;
}
</style>

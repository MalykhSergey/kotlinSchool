<template>
  <div id="app">
    <nav-bar  v-bind:user="user"></nav-bar>
    <div class="container">
      <router-view @userLogin="login"/>
    </div>
  </div>
</template>
<script>
import NavBar from "@/pages/components/NavBar";

export default {
  name: "App",
  components:{NavBar},
  data: function () {
    return {
      Authorization: "",
      user: {
        status: false,
        name: "",
        type: null,
      },
      url: "http://127.0.0.1:80/login"
    }
  },
  methods:{
    login: function (value, userTypePromise, nickName){
      this.user.status = true
      this.Authorization = value
      userTypePromise.then((data) => {
        this.user.type = data
      })
      this.user.name = nickName
    }
  }
}
</script>
<style>
</style>

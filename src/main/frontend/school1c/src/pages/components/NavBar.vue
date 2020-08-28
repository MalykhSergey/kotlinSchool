<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand>NavBar</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/">Главная</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown v-if="user.status" right>
            <template v-slot:button-content>
              <em>{{ user.name }}</em>
            </template>
            <b-dropdown-item to="/profile">Профиль</b-dropdown-item>
            <b-dropdown-item to="/logout">Выйти</b-dropdown-item>
          </b-nav-item-dropdown>
          <div v-else>
            <b-nav-item to="/login">Войти</b-nav-item>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  props: ["user"]
}
</script>

<style scoped>

</style>
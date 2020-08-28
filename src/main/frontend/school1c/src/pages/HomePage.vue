<template>
  <div>
    <admin-home-page v-if="this.$parent.user.type == 0 || this.$parent.user.type == 3"></admin-home-page>
  </div>
</template>

<script>
import AdminHomePage from "@/pages/AdminHomePage";

export default {
  name: 'HomePage',
  components: {AdminHomePage}
}
</script>
<style scoped>
</style>

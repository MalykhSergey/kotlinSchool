<template>
  <div>
    <h3>
      <router-link to="/createSchool">Создать школу</router-link>
    </h3>
    <h3>
      <router-link to="/createSchoolClass">Создать класс</router-link>
    </h3>
    <h3>
      <router-link to="/createUser">Создать пользователя</router-link>
    </h3>
    <h3>
      <router-link to="/addSchoolClassForTeacher">Привязать учителя</router-link>
    </h3>
    <h3>
      <router-link to="/addStudentForSchoolClass">Привязать ученика</router-link>
    </h3>
  </div>
</template>

<script>
export default {
  name: "AdminHomePage"
}
</script>

<style scoped>

</style>
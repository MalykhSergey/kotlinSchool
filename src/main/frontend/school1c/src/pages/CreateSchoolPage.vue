<template>
  <div class="mt-5">
    <b-form>
      <b-form-group
          label="Название школы"
          label-for="schoolName">
        <b-form-input id="schoolName"
                      v-model.trim="schoolName"
                      :state="schoolName.length>10 &&
         schoolName.length < 30">
        </b-form-input>
        <b-form-invalid-feedback :state="schoolName.length>10 &&
         schoolName.length < 30">
          Введите название длиннее 10 символов и короче 30
        </b-form-invalid-feedback>
      </b-form-group>
      <b-button variant="primary">Создать</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "CreateSchoolPage",
  data: function () {
    return {
      schoolName: ""
    }
  },
  props: {
    Authorization:String,
  },
  methods:{
    createSchool:function (){
      if (this.schoolName.length>10 && this.schoolName.length){
        fetch("127.0.0.1")
      }
    }
  }
}
</script>

<style scoped>

</style>
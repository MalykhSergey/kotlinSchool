<template>
  <div>
    <div class="mt-5">
      <b-form v-if="show">
        <b-form-group id="input-group-1" label="Ваш Ник:" label-for="input-1">
          <b-form-input
              id="input-1"
              v-model.trim="form.nickName"
              :state="form.nickName.length > 10 && form.nickName.length < 30"
              placeholder="Введите ник"
          ></b-form-input>
          <b-form-invalid-feedback :state="form.nickName.length > 10 && form.nickName.length < 30">
            {{nicknameAlert}}
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group id="input-group-2" label="Ваш пароль:" label-for="input-2">
          <b-form-input
              id="input-2"
              v-model.trim="form.password"
              placeholder="Введите пароль"
              type="password"
              :state="form.password.length > 10 && form.password.length < 30"
          ></b-form-input>
          <b-form-invalid-feedback :state="form.password.length > 10 && form.password.length < 30">
            {{passwordAlert}}
          </b-form-invalid-feedback>
        </b-form-group>
        <b-button @click="sendData" variant="primary">Отправить</b-button>
      </b-form>
      <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
      </b-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  computed: {
    passwordAlert: function () {
      if (this.form.password.length > 30) {
        return "Ваш пароль должен быть короче 30 символов"
      }
      if (this.form.password.length < 10) {
        return "Ваш пароль должен быть длиннее 10 символов"
      } else return null
    },
    nicknameAlert: function () {
      if (this.form.nickName.length > 30) {
        return "Ваш ник должен быть короче 30 символов"
      }
      if (this.form.nickName.length < 10) {
        return "Ваш ник должен быть длиннее 10 символов"
      } else return null
    }
  },
  data() {
    return {
      form: {
        nickName: '',
        password: '',
      },
      show: true,
      url: this.$parent.url
    }
  },
  methods: {
    sendData: function () {
      let Authorization = "Basic " + btoa(this.form.nickName + ':' + this.form.password);
      fetch(this.url, {
        method: "GET",
        headers: {
          Authorization
        }
      }).then(
          (response) => {
            if (response.status == 202) {
              this.$emit("userLogin", Authorization, response.text(), this.form.nickName)
              this.$router.push("/")
            }
          }
      )
    }
  }
}
</script>

<style scoped>

</style>
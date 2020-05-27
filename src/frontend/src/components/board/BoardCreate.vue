<template>
  <div>
    <b-form @submit="writeClick" @reset="writeCancel">
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="TITLE" label-for="input-title">
        <b-form-input
          id="input-title"
          size="lg"
          v-model="title"
          :counter="50"
          maxlength="50"
          required
          placeholder="Enter title"/>
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="CONTENTS" label-for="input-cont">
        <b-form-textarea
          id="input-cont"
          size="lg"
          v-model="contents"
          placeholder="Enter something..."
          rows="4"
          max-rows="6"
          :counter="1000"
          maxlength="1000"
          required/>
      </b-form-group>
      <div class="form-btn">
        <b-button squared variant="outline-dark" size="lg" @click="writeClick">Submit</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="writeCancel">Reset</b-button>
      </div>
    </b-form>
  </div>
</template>


<script>
import axios from 'axios'
export default {
  name: 'BoardCreate',
  methods: {
    writeClick () {
      axios.put('http://localhost:8080/api/insert', this.$data)
      .then((response) => {
        console.log(response);
        this.$router.push('/');
      })
      .catch((error) => {
        console.log(error);
      });
    },
    writeCancel () {
      this.$router.push('/');
    }
  },
  data () {
    return {
      title: '',
      contents: ''
    }
  }
}
</script>
<style>
.form-btn button {
  float: right;
  margin-right: 0.8rem;
}
</style>
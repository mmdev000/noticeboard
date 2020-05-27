<template>
  <div>
    <b-form>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="TITLE" label-for="label-title">
        <b-form-input
          id="input-title"
          size="lg"
          v-model="data.title"
          :counter="50"
          maxlength="50"
          />
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="CONTENTS" label-for="input-cont">
        <b-form-textarea
          id="input-cont"
          size="lg"
          v-model="data.contents"
          rows="4"
          max-rows="6"
          :counter="1000"
          maxlength="1000"
          />
      </b-form-group>

      <div class="form-btn">
        <b-button squared variant="outline-dark" size="lg" @click="updateClick">Update</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="deleteClick">Delete</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="backClick">Back</b-button>
      </div>
    </b-form>
  </div>
</template>


<script>
import axios from 'axios'

export default {
  name: 'BoardView',
  created () {
    this.fetch();
  },
  methods: {
    fetch() {
      axios.get('http://localhost:8080/api/get/'+this.$route.params.id)
      .then((response) => {
        console.log(response);
        this.data = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    },
    updateClick () {
      if (confirm('정말 수정하시겠습니까?')) {
        axios.put('http://localhost:8080/api/update', this.data)
        .then((response) => {
          console.log(response);
          this.$router.push('/');
        })
        .catch((error) => {
          console.log(error);
        });
      }
    },
    deleteClick () {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios.delete('http://localhost:8080/api/delete/' + this.data.id)
          .then((response) => {
            console.log(response);
            this.$router.push('/');
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    backClick () {
      this.$router.push('/');
    }
  },
  data () {
    return {
      data: {}
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
<template>
  <form>
    
    제목
    <b-input :counter="50" label="제목" name="title" required v-model="title" maxlength="50" />
    
    내용
    <b-textarea filled name="contents" v-model="contents" :counter="1000" maxlength="1000" />
    <b-button block outlined color="blue" @click="updateClick"> 수정 </b-button>
    <b-button block outlined color="blue" @click="deleteClick"> 삭제 </b-button>
    <b-button block outlined color="blue" @click="updateCancel"> 취소 </b-button>
  
  </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardUpdate',
  created () {
    this.fetch();
  },
  methods: {
    fetch() {
      axios.get('http://localhost:8080/api/get/'+this.$route.params.id)
      .then((response) => {
        console.log(response);
        this.data = response;
      })
      .catch((error) => {
        console.log(error);
      });
    },
    updateClick () {
      axios.put('http://localhost:8080/api/update', this.$data)
      .then((response) => {
        console.log(response);
        this.$router.push('/');
      })
      .catch((error) => {
        console.log(error);
      });
    },
    deleteClick () {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios.delete('http://localhost:8080/api/delete/'+this.$route.params.id)
          .then((response) => {
            console.log(response);
            this.$router.push('/');
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    updateCancel () {
      this.$router.push('/');
    }
  },
  data () {
    return {
      id: this.$route.params.id,
      title: '',
      contents: ''
    }
  }
}
</script>
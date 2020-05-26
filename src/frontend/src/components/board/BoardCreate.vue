<template>
  <form>
    
      제목
      <b-input :counter="50" label="제목" name="title" required v-model="title" maxlength="50" />
      
      내용
      <b-textarea filled name="contents" hint="내용을 입력해주세요." v-model="contents" :counter="1000" maxlength="1000" />
      <b-button block outlined color="blue" @click="writeClick"> 등록 </b-button>
      <b-button block outlined color="blue" @click="writeCancel"> 취소 </b-button>
    
  </form>
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
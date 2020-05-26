<template>
  <div id="app">
    <div v-if="hasResult">
      <div v-for="post in posts" v-bind:key="post.id">
        <h1></h1>
        <p></p>
      </div>
    </div>
    <button v-else v-on:click="searchTerm">글 불러오기</button>
  </div>
</template>

<script>


export default {
  name: 'app',
  data: function () {
    return {
      posts: []
    }
  },
  computed: {
    hasResult: function () {
      return this.posts.length > 0
    }
  },
  methods: {
    searchTerm: function () {
      // using JSONPlaceholder
      const baseURI = 'http://localhost:8080/api';
      this.$http.get(`${baseURI}/getList`)
      .then((result) => {
        console.log(result)
        this.posts = result.data
      })
    }
  }
}
</script>
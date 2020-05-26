<template>
  <div>
    <h1>NOTICE BOARD</h1>
    <paginated-list :list-array="pageArray"/>
  </div>
</template>

<script>
import axios from 'axios'
import PaginatedList from './PaginatedList'

export default {
  name: 'simple-pagination',
  components: {
    PaginatedList
  },
  data () {
    return {
      pageArray: []
    }
  },
  created () {
    axios.get('http://localhost:8080/api/getList')
    .then(response => {
      console.log(response);
      this.pageArray = response.data;
    })
    .catch(err => {
      console.log(err);
    });
  }
}
</script>

<style scoped>

</style>
<template>
  <div>
    <div class="btn-cr">
      <b-button squared variant="outline-dark" size="lg" @click="writeContent">writing</b-button>
    </div>
    <hr>
    <b-table-simple>
      <b-tr>
        <b-th>NO</b-th>
        <b-th>TITLE</b-th>
        <b-th>CONTENTS</b-th>
        <b-th>REGDATE</b-th>
        <b-th>CHGDATE</b-th>
      </b-tr>
      <b-tr v-for="p in paginatedData" :key="p.id" @click="boardView(p.id)">
        <b-td>{{ p.id }}</b-td>
        <b-td>{{ p.title }}</b-td>
        <b-td>{{ p.contents }}</b-td>
        <b-td>{{ p.regdate }}</b-td>
        <b-td>{{ p.chgdate }}</b-td>
      </b-tr>
    </b-table-simple>
    <div class="btn-cover">
      <b-button squared variant="outline-dark" :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        prev
      </b-button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} Page</span>
      <b-button squared variant="outline-dark" :disabled="pageNum >= pageCount -1" @click="nextPage" class="page-btn">
        next
      </b-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'paginated-list',
  data () {
    return {
      pageNum: 0
    }
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    writeContent () {
      this.$router.push({
        path: '/board/create'
      })
    },
    boardView (id) {
      this.$router.push({
        path: '/board/view/' + id
      })
    }
  },
  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      
      if (listLeng % listSize > 0) {
        page += 1;
      }
      
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
    
      return this.listArray.slice(start, end);
    }
  }
}
</script>

<style scoped>
.btn-cr {
  padding: 1rem;
  text-align: right;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2.5rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
} 
hr {
  border:thin solid orange;
  margin-top: 0rem;
  margin-bottom: 0rem;
}
.hr-text {
  margin: 0rem 0.8rem;
  padding: 0rem;
  text-align: left;
  font-size: 2.5rem
}
</style>
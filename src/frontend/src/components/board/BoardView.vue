<template>
  <div label-size="lg">
    <p class="hr-text">view details</p>
    <hr>
    <b-form>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="title">
        <p class="data-txt">{{data.title}}</p>
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="contents">
        <p class="data-txt">{{data.contents}}</p>
      </b-form-group>

      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg">
        <img id="output">
      </b-form-group>
      
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="attached files">
        <a class="data-txt" href="#" @click="fileDownload(name)" v-for="name in data.fileNames" v-bind:key="name">{{ name }} <br></a>
      </b-form-group>

      <div class="form-btn">
        <b-button squared variant="outline-dark" size="lg" @click="editClick(data.id)">edit</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="deleteClick">delete</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="backClick">back</b-button>
      </div>
    </b-form>
    <br><br>
    <p class="hr-text">comments</p>
    <hr>
    <comment-view :bId="this.$route.params.id"/>
  </div>
</template>


<script>
import axios from 'axios'
import CommentView from '../comment/CommentView'

export default {
  name: 'BoardView',
  components: {
    CommentView
  },
  created () {
    this.fetch();
  },
  methods: {
    fetch() {
      axios.get('http://localhost:8080/api/get/' + this.$route.params.id)
      .then((response) => {
        console.log(response);
        this.data = response.data;

        // if (this.data.imgpath != null) {
        //   this.imgView(this.data.imgpath);
        // }
      })
      .catch((error) => {
        console.log(error);
      });
    },
    fileDownload (name) {
      axios({
      url: 'http://localhost:8080/api/fileDownload/' + this.data.id + '/' + name,
      method: 'GET',
      responseType: 'blob', // important
    }).then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', name);
      document.body.appendChild(link);
      link.click();
    });
    },
    // imgView (imgPath) {
    //   var arrExt = new Array("bmp", "gif", "jpg", "png", "jpeg");
    //   const output = document.getElementById('output');

    //   for (var i in arrExt) {
    //     if (imgPath.split('.')[1] == arrExt[i]) {
    //       output.src = 'http://localhost:8080/api/getImg/' + this.data.id;
    //       output.style.height = '10rem';
    //     }
    //   }
    // },
    editClick (id) {
      this.$router.push({
        path: '/board/update/' + id
      });
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
hr {
  border:thin solid orange;
  margin-top: 0rem;
}
.hr-text {
  margin: 0rem 0.8rem;
  padding: 0rem;
  text-align: left;
  font-size: 2.5rem
}
small {
  float: right;
}

.comment {
  text-align: left;
}

#fileDownText {
  float: left;
}

.data-txt {
  float: left;
  margin-top: 0.7rem;
}

</style>
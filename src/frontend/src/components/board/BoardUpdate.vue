<template>
  <div label-size="lg">
    <p class="hr-text">modify</p>
    <hr>
    <b-form>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="title" label-for="label-title">
        <b-form-input
          id="input-title"
          size="lg"
          v-model="data.title"
          :counter="50"
          maxlength="50"
          />
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="contents" label-for="input-cont">
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

      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="files">
        <b-form-file type="file" v-model="data.files" @change="selectedFile"/>
      </b-form-group>

      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg">
        <img id="output">
      </b-form-group>
      
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="attached files">
        <a href="#" @click="fileDownload(name)" v-for="name in data.fileNames" v-bind:key="name">{{ name }} <br></a><a href="#">X</a>
      </b-form-group>

      <div class="form-btn">
        <b-button squared variant="outline-dark" size="lg" @click="editClick">edit</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="backClick">back</b-button>
      </div>
    </b-form>
  </div>
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
      axios.get('http://localhost:8080/api/get/' + this.$route.params.id)
      .then((response) => {
        console.log(response);
        this.data = response.data;
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
    editClick () {
      if (confirm('정말 수정하시겠습니까?')) {
        const formData = new FormData();
        formData.append('id', this.data.id);
        formData.append('title', this.data.title);
        formData.append('contents', this.data.contents);
        formData.append('imgpath', this.data.imgpath);

        if (this.data.files != null) {
          formData.append('files', this.data.files);
        }
        
        axios.put('http://localhost:8080/api/update', formData, {headers: { 'Content-Type': 'multipart/form-data' }})
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
      this.$router.go(-1)
    },
    selectedFile: function (event) {
      const output = document.getElementById('output');
      const file = event.target.files[0];
    
      if (file.type.match('image.*')) {
        const reader = new FileReader();
        reader.addEventListener('load', event => {
          output.src = event.target.result;
          output.style.height = '10rem';
        });
        reader.readAsDataURL(file);
      } else {
        output.src = '';
      }
    }
  },
  data () {
    return {
      data: {},
      commentList: [{
        bid: '',
        id: '',
        comment: '',
        reguser: '',
        chgdate: ''
      }]
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
</style>
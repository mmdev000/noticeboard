<template>
  <div>
    <p class="hr-text">writing</p>
    <hr>
    <b-form method="post" enctype="multipart/form-data" @submit="writeClick" @reset="writeCancel">
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="title" label-for="input-title">
        <b-form-input
          id="input-title"
          size="lg"
          v-model="title"
          :counter="50"
          maxlength="50"
          required
          placeholder="Enter title"/>
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="contents" label-for="input-cont">
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
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="files">
        <!-- <b-form-file multiple :file-name-formatter="formatNames" v-model="files"></b-form-file> -->
        <b-form-file type="file" v-model="files" @change="selectedFile"/>
      </b-form-group>
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg">
        <img id="output">
      </b-form-group>
      <div class="form-btn">
        <b-button squared variant="outline-dark" size="lg" @click="writeClick">submit</b-button>
        <b-button squared variant="outline-dark" size="lg" @click="writeCancel">reset</b-button>
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
      const formData = new FormData();
      formData.append('title', this.$data.title);
      formData.append('contents', this.$data.contents);

      if (this.$data.files != null) {
        formData.append('files', this.$data.files);
      }
      
      axios.post('http://localhost:8080/api/insert', formData, {headers: { 'Content-Type': 'multipart/form-data' }})
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
      title: '',
      contents: '',
      files: null
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
</style>
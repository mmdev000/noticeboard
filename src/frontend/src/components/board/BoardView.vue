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

      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="Files">
        <!-- <b-form-file multiple :file-name-formatter="formatNames" v-model="files"></b-form-file> -->
        <b-form-file type="file" v-model="data.files" @change="selectedFile"/>
      </b-form-group>

      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg">
        <img id="output">
      </b-form-group>
      
      <b-form-group label-cols="4" label-cols-lg="2" label-size="lg">
        <label>{{ data.fileNames }}</label>
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

        if (this.data.imgpath != null) {
          this.imgView(this.data.imgpath);
        }
      })
      .catch((error) => {
        console.log(error);
      });
    },
    imgView (imgPath) {
      var arrExt = new Array("bmp", "gif", "jpg", "png", "jpeg");
      const output = document.getElementById('output');

      for (var i in arrExt) {
        if (imgPath.split('.')[1] == arrExt[i]) {
          output.src = 'http://localhost:8080/api/getImg/' + this.data.id;
          output.style.height = '10rem';
        }
      }
    },
    updateClick () {
      if (confirm('정말 수정하시겠습니까?')) {

        console.log(this.data)
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
<template>
  <div class="comment-group">
    <b-input-group prepend="commenting" class="mt-3">
      <b-form-input 
        id="input-comment" v-model="commentList.comment"/>
      <b-input-group-append>
        <b-button squared variant="outline-dark" @click="addClick(null)">add comment</b-button>
      </b-input-group-append>
    </b-input-group>

    <br><br>
    <div id="comment-list">
      <b-list-group>
        <b-list-group-item class="flex-column align-items-start" v-for="comment in commentList" v-bind:key="comment.id">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{comment.reguser}}</h5>
            <small>{{comment.chgdate}}</small>
          </div>

          <small class="reply-group">
            <span @click="replyClick(comment.id)">reply</span> &nbsp;
            <span @click="editClick(comment.id)">edit</span> &nbsp;
            <span @click="deleteClick(comment.id)">delete</span>
          </small>

          <div :id="'com-txt-'+comment.id">
            <p class="mb-1 comment">
              {{comment.comment}}
            </p>
          </div>

          <div :id="'com-input-group-'+comment.id">
            <b-input-group class="mt-3">
              <b-form-input :id="'com-input-'+comment.id" v-model="comment.comment"/>
              <b-input-group-append>
                <b-button squared variant="outline-dark" @click="updateClick(comment.id, null)">edit comment</b-button>
                <b-button squared variant="outline-dark" @click="cancelClick(comment.id)">cancel</b-button>
              </b-input-group-append>
            </b-input-group>
          </div>


          <!-- reply -->
          <div v-if="comment.replyList.length > 0">
            <b-list-group-item class="flex-column align-items-start" v-for="reply in comment.replyList" v-bind:key="reply.id">
              <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">{{reply.reguser}}</h5>
                <small>{{reply.chgdate}}</small>
              </div>

              <small class="reply-group">
                <span @click="editClick(reply.id)">edit</span> &nbsp;
                <span @click="deleteClick(reply.id)">delete</span>
              </small>

              <div :id="'com-txt-'+reply.id">
                <p class="mb-1 comment">
                  {{reply.comment}}
                </p>
              </div>

              <div :id="'com-input-group-'+reply.id">
                <b-input-group class="mt-3">
                  <b-form-input :id="'com-input-'+reply.id" v-model="reply.comment"/>
                  <b-input-group-append>
                    <b-button squared variant="outline-dark" @click="updateClick(reply.id, reply.pid)">edit reply</b-button>
                    <b-button squared variant="outline-dark" @click="cancelClick(reply.id)">cancel</b-button>
                  </b-input-group-append>
                </b-input-group>
              </div>
            </b-list-group-item>
          </div>
          <!-- reply -->

          <!-- reply add input -->
          <div :id="'re-input-group-'+comment.id">
            <b-input-group prepend="enter reply" class="mt-3">
              <b-form-input 
                :id="'re-input-'+comment.id" v-model="comment.replyList.comment"/>
              <b-input-group-append>
                <b-button squared variant="outline-dark" @click="addClick(comment.id)">add reply</b-button>
                <b-button squared variant="outline-dark" @click="replyCancelClick(comment.id)">cancel</b-button>
              </b-input-group-append>
            </b-input-group>
          </div>
          <!-- reply add input -->
          

        </b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>


<script>
import axios from 'axios'
import $ from 'jquery'

export default {
  name: 'CommentView',
  props: ['bId'],
  created () {
    this.commentFetch();
  },
  methods: {
    commentFetch () {
      axios.get('http://localhost:8080/api/comment/getList/' + this.bId)
      .then((response) => {
        console.log(response);
        this.commentList = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    },
    addClick (pid) {
      console.log('pid:',pid);
      let comText;
      
      if (pid == null) {
        console.log('pid == null');
        comText = this.commentList.comment;
        console.log('comText:',comText);
      } else {
        console.log('pid != null');
        comText = document.getElementById('re-input-'+pid).value;
        // comText = this.commentList.replyList;
        console.log('comText:',comText);
      }
      if (comText == '' || comText == null) {
        alert('댓글 내용을 입력하세요');
        return;
      }
      this.commentList = {
        bid: this.bId,
        pid: pid,
        comment: comText,
        reguser: 'mmdev',
      };
      axios.post('http://localhost:8080/api/comment/insert', this.commentList)
      .then((response) => {
        console.log(response);
        // window.location.reload();
        $('#comment-list').load(window.location.href + '#comment-list');

      })
      .catch((error) => {
        console.log(error);
      });
    },
    replyClick (comRowNum) {
      const reInput = document.getElementById("re-input-group-"+comRowNum);
      // const reTxt = document.getElementById("re-txt-"+comRowNum);
      reInput.style.display = "inline";
      // reTxt.style.display = "none";
    },
    editClick (comRowNum) {
      const comInput = document.getElementById("com-input-group-"+comRowNum);
      const comTxt = document.getElementById("com-txt-"+comRowNum);
      comInput.style.display = "inline";
      comTxt.style.display = "none";
    },
    updateClick (comRowNum, pid) {
      console.log('pid:',pid);
      const comInput = document.getElementById("com-input-"+comRowNum);
      this.commentList = {
        id: comRowNum,
        pid: pid,
        comment: comInput.value,
        reguser: 'mmdev',
      };
      if (confirm('정말 수정하시겠습니까?')) {
        axios.post('http://localhost:8080/api/comment/update', this.commentList)
        .then((response) => {
          console.log(response);
          $('#comment-list').load(window.location.href + '#comment-list');
        })
        .catch((error) => {
          console.log(error);
        });
      }
    },
    deleteClick (comRowNum) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios.get('http://localhost:8080/api/comment/delete/' + comRowNum)
        .then((response) => {
          console.log(response);
          $('#comment-list').load(window.location.href + '#comment-list');
        })
        .catch((error) => {
          console.log(error);
        });
      }
    },

    cancelClick (comRowNum) {
      const comInput = document.getElementById("com-input-group-"+comRowNum);
      const comTxt = document.getElementById("com-txt-"+comRowNum);
      comInput.style.display = "none";
      comTxt.style.display = "inline";
      comTxt.style.float = "left";
    },
    replyCancelClick (comRowNum) {
      const reInput = document.getElementById("re-input-group-"+comRowNum);
      // const reTxt = document.getElementById("re-txt-"+comRowNum);
      reInput.style.display = "none";
      // reTxt.style.display = "inline";
      // reTxt.style.float = "left";
    }
  },
  data () {
    return {
      commentList: [{
        bid: '',
        id: '',
        pid: '',
        replyList: [{comment:''}],
        comment: '',
        reguser: '',
        chgdate: ''
      }]
    }
  }
}
</script>
<style>
small {
  float: right;
}
.comment {
  text-align: left;
}
[id^='com-input-group'] {
  display: none;
}
.reply-group span:hover {
  color: orange;
  cursor: pointer;
}
[id^='re-input-group'] {
  display: none;
}
</style>
<template>
  <div>
      <b-button v-b-modal.modal-1>Launch demo modal</b-button>

      <b-modal id="modal-1" :title="selectedBtn.type" @ok="confirmModal">
        <p class="my-4">{{selectedBtn.type}}</p>
      </b-modal>
    </div>
</template>
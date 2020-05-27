import Vue from "vue";
import VueRouter from "vue-router";
import SimplePagination from "@/components/board/SimplePagination";
import BoardCreate from "@/components/board/BoardCreate";
import BoardView from "@/components/board/BoardView";

Vue.use(VueRouter); // 뷰 어플리케이션에 라우터 플러그인 추가

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "SimplePagination",
      component: SimplePagination
    },
    {
      path: "/board/create",
      name: "BoardCreate",
      component: BoardCreate
    },
    {
      path: "/board/view/:id",
      name: "BoardView",
      component: BoardView
    }
  ]
});
<template>
  <div id="favorites">
    <follow-list />
    <div id="fav-main">
      <post
        v-bind:key="post.postId"
        v-bind:post="post"
        v-for="post in $store.state.posts"
      />
    </div>
  </div>
</template>

<script>
import postService from "../services/PostService.js";
import FollowList from "../components/FollowList.vue";
import Post from "../components/Post.vue";
export default {
  name: "home",
  created() {
    postService
      .listFavorites()
      .then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
        console.log(error);
      });
  },
  components: { Post, FollowList },
};
</script>

<style>
#favorites {
  display: grid;
  grid-column: 1fr 1fr 1fr;
  grid-template-areas: "follow-list main .";
}

h1 {
  grid-area: title;
}

#home follow-list {
  grid-area: follow-list;
  position: fixed;
}

#fav-main {
  grid-area: main;
  display: flex;
  flex-direction: column;
  grid-gap: 10px;
}
</style>
<template>
  <div id="post-details-page">
    <post-detail :post="$store.state.post" />
  </div>
</template>

<script>
import postService from "../services/PostService.js";
import PostDetail from "../components/PostDetail.vue";
export default {
  name: "postDetailsPage",
  created() {
    postService
      .getPost(this.$route.params.postId)
      .then((response) => {
        this.$store.commit("SET_POST", response.data);
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
        console.log(error);
      });
  },
  components: { PostDetail },
};
</script>

<style scoped>
#post {
  margin: auto;
}
</style>

<!--
redirect here from profile when image is clicked
show one post with all the deets
-->
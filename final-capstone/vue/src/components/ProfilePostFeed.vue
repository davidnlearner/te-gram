<template>
  <div id="profile-post-feed">
    <ul :style="gridStyle" class="photofeed">
      <li
        class="photofeed-item"
        v-for="post in filteredPosts"
        v-bind:key="post.id"
      >
        <img
          class="profile-post-photo"
          v-bind:src="post.images[0]"
          v-on:click="$router.push(`/postDetails/${post.postId}`)"
        />
        <!--<post-detail id="post-detail${post.id}" v-if="clicked"/>-->
      </li>
    </ul>
  </div>
</template>

<script>
import postService from "../services/PostService.js";

export default {
  props: {
    profileUsername: String,
  },
  created() {
    postService
      .listPosts()
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
  computed: {
    gridStyle() {
      return {
        gridTemplateColumns: `repeat(3, minmax(100px, 1fr))`,
      };
    },
    filteredPosts() {
      let filteredPosts = this.$store.state.posts;

      filteredPosts = filteredPosts.filter(
        (post) =>
          post.username.toLowerCase() === this.profileUsername.toLowerCase()
      );

      return filteredPosts;
    },
  },
};
</script>

<style scoped>
.photofeed {
  display: grid;
  grid-gap: 1em;
}

ul {
  list-style-type: none;
}

.profile-post-photo {
  height: 250px;
  max-width: 300px;
  border-radius: 8px;
}
</style>
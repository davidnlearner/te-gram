<template>
  <div id="home-div">
    <follow-list id="follow-list" />
    <div id="filter-posts-box">
      <h3>Filter Posts by User</h3>
      <label for="filter-posts-username"></label>
      <input
        type="text"
        name="filter-posts-username"
        id="filter-posts-username"
        maxlength="30"
        v-model="filter.username"
      />
      <!--
      <label for="filter-posts-dateposted">Date: </label>
      <input
        type="date"
        name="filter-posts-dateposted"
        id="filter-posts-dateposted"
        v-model="filter.dateposted"
      /> 
      -->
    </div>
    <div id="main">
      <post
        v-bind:key="post.postId"
        v-bind:post="post"
        v-for="post in filteredPosts"
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
  data() {
    return {
      filter: {
        username: "",
        dateposted: "",
      },
    };
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
  components: { Post, FollowList },
  computed: {
    filteredPosts() {
      let filteredPosts = this.$store.state.posts;
      if (this.filter.username != "") {
        filteredPosts = filteredPosts.filter((post) =>
          post.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }

      return filteredPosts;
    },
  },
};
</script>

<style>
#home-div {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "follow-list main filter-box";
}

#filter-posts-box {
  display: flex;
  align-items: center;
  flex-direction: column;
  grid-area: filter-box;
  margin-top: 2rem;
  padding: 0.5rem;
  background-color: rgb(241, 248, 243);
  border-radius: 15px;
  width: 15rem;
  height: 6rem;
}

#filter-posts-box h3 {
  font-size: 1.2rem;
  margin: 1.3rem 0;
}

#home-div #follow-list {
  grid-area: follow-list;
  position: fixed;
}

#main {
  grid-area: main;
  display: flex;
  align-items: center;
  flex-direction: column;
  grid-gap: 10px;
}

#debug {
  background-color: yellow;
}

@media only screen and (max-width: 1024px) {
  #follow-list {
    display: none;
  }

  #home-div {
    grid-template-areas:
      ". filter-box ."
      ". main .";
  }
}
</style>
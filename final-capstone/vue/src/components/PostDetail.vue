<template>
  <div id="post-detail">
    <h2 id="username" v-on:click="$router.push(`/profile/${post.username}`)">
      {{ post.username }}
    </h2>

    <!-- carousel goes here, Vueper Slides guide: https://antoniandre.github.io/vueper-slides/ -->
    <vueper-slides
      id="image-slideshow"
      class="no-shadow"
      :slide-ratio="1 / 4"
      fixed-height="500px"
    >
      <vueper-slide
        v-for="image in post.images"
        :key="image.id"
        :content="tagImage(image)"
      />
    </vueper-slides>

    <div id="button-box">
      <button
        id="like"
        class="bluehover"
        v-on:click="
          {
            unlikePost();
          }
        "
        v-if="liked"
      >
        <i class="fas fa-heart"></i>
      </button>
      <button
        id="like"
        class="bluehover"
        v-on:click="
          {
            likePost();
          }
        "
        v-else
      >
        <i class="far fa-heart"></i>
      </button>
      <button
        id="comments-link"
        class="bluehover"
        v-on:click="$router.push(`/postDetails/${post.postId}`)"
      >
        <i class="far fa-comment"></i>
      </button>
    </div>
    <button
      id="favorite-btn"
      class="bluehover"
      v-on:click="
        {
          unfavoritePost();
        }
      "
      v-if="favorited"
    >
      <i class="fas fa-bookmark"></i>
    </button>
    <button
      id="favorite-btn"
      class="bluehover"
      v-on:click="
        {
          favoritePost();
        }
      "
      v-else
    >
      <i class="far fa-bookmark"></i>
    </button>
    <div id="box-4-likes">
      <p>{{ post.likeNumber }} Likes</p>
    </div>
    <p id="datetime">{{ timeSince }} ago</p>

    <div id="caption">
      <p>{{ post.caption }}</p>
    </div>

    <comments
      id="comments"
      :postId="post.postId"
      :commentList="post.comments"
    />
  </div>
</template>

<script>
import postService from "../services/PostService.js";
import Comments from "../components/Comments.vue";
import { VueperSlides, VueperSlide } from "vueperslides";
import "vueperslides/dist/vueperslides.css";

export default {
  props: {
    post: Object,
  },
  components: { VueperSlides, VueperSlide, Comments },
  computed: {
    liked() {
      return this.post.liked;
    },
    favorited() {
      return this.post.favorited;
    },
    timeSince() {
      let seconds = this.post.secondsAgo;

      var interval = seconds / 31536000;

      if (interval > 1) {
        return Math.floor(interval) + " years";
      }
      interval = seconds / 2592000;
      if (interval > 1) {
        return Math.floor(interval) + " months";
      }
      interval = seconds / 86400;
      if (interval > 1) {
        return Math.floor(interval) + " days";
      }
      interval = seconds / 3600;
      if (interval > 1) {
        return Math.floor(interval) + " hours";
      }
      interval = seconds / 60;
      if (interval > 1) {
        return Math.floor(interval) + " minutes";
      }
      return Math.floor(seconds) + " seconds";
    },
  },
  methods: {
    tagImage(url) {
      return `<img src="${url}" class="slide-img"/>`;
    },
    likePost() {
      console.log(this.post.images);
      postService
        .addLiked(this.post.postId)
        .then((response) => {
          if (response.status == 201) {
            this.$store.commit("TOGGLE_LIKE", this.post);
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    unlikePost() {
      postService
        .removeLiked(this.post.postId)
        .then()
        .catch((error) => {
          console.log(error.response);
        });
      this.$store.commit("TOGGLE_LIKE", this.post);
    },
    favoritePost() {
      postService
        .addFavorite(this.post.postId)
        .then()
        .catch((error) => {
          console.log(error.response);
        });

      this.$store.commit("TOGGLE_FAVORITE", this.post);
    },
    unfavoritePost() {
      postService
        .removeFavorite(this.post.postId)
        .then()
        .catch((error) => {
          console.log(error.response);
        });

      this.$store.commit("TOGGLE_FAVORITE", this.post);
    },
  },
};
</script>

<style>
#post-detail {
  margin: 2rem auto;
  grid-gap: 0.5rem;
  width: 800px;
  border-radius: 10px;
  background-color: rgb(241, 248, 243);
  display: grid;
  grid-template-columns: 4fr 1fr 1fr;
  grid-template-areas:
    "image username ."
    "image caption caption"
    "image  comments comments"
    "image comments comments"
    "image btns favorite-btn"
    "image likes ."
    "image datetime ."
    "image comment-form comment-form";
}

#post-detail #username {
  font-size: 1.5rem;
  grid-area: username;
  margin: 1rem 0;
}

#datetime {
  grid-area: datetime;
}

#caption {
  grid-area: caption;
  padding: 4px;
  margin: 0;
}

#image-slideshow {
  grid-area: image;
  background-color: rgb(241, 248, 243);
  justify-content: center;
}

.slide-img {
  height: 400px;
}

.slide-img {
  max-height: 100%;
  max-width: 100%;
  margin-bottom: 10px;
}

#button-box {
  grid-area: btns;
  display: flex;
  justify-content: space-evenly;
}

#favorite-btn {
  grid-area: favorite-btn;
  justify-self: center;
  width: 2rem;
  font-size: 1.8rem;
}

#like,
#comments-link,
#share {
  font-size: 1.8rem;
}

#box-4-likes {
  grid-area: likes;
  padding: 4px;
}

#box-4-likes p,
#caption p {
  margin: 0;
}

#comments {
  grid-area: comments;
}

.submit-btn {
  align-self: flex-end;
}

.bluehover:hover,
.submit-btn:hover {
  background-color: #00adee;
}

button {
  border: 0;
  background-color: rgb(241, 248, 243);
}
</style>
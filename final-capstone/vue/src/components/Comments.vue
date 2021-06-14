<!-- Cannot remove comment until after refresh, need commentId for delete endpoint atm -->
<template>
  <div id="comments">
    <p><i class="fas fa-comments"></i> {{ commentList.length }}</p>
    <div class="comment-wrapper">
      <div
        class="comment"
        v-for="comment in commentList"
        v-bind:key="comment.commentId"
      >
        <p>
          <span class="username">{{ comment.username }}</span>
          {{ comment.message }}
        </p>
        <p>
          <span id="time-since"> {{ timeSince(comment) }} </span>
          <i
            id="delete-comment-btn"
            v-on:click="deleteComment(comment)"
            class="far fa-times-circle"
            v-if="comment.userId == userId"
          ></i>
        </p>
      </div>
    </div>

    <form
      class="comment-form"
      autocomplete="off"
      v-on:submit.prevent="submitComment"
    >
      <textarea
        class="comment-input"
        name="comment-input"
        v-model="newComment"
        placeholder="Add a comment..."
      />
      <input class="submit-btn" type="submit" value="Submit" />
    </form>
  </div>
</template>

<script>
import postService from "@/services/PostService";
export default {
  props: {
    postId: Number,
  },
  data() {
    return {
      userId: this.$store.state.user.id,
      newComment: "",
    };
  },
  computed: {
    commentList() {
      const post = this.$store.state.posts.find(
        (post) => post.postId === this.postId
      );
      console.log(this.postId);
      return post.comments;
    },
  },
  methods: {
    submitComment() {
      const theComment = {
        userId: this.$store.state.user.id,
        username: this.$store.state.user.username,
        message: this.newComment,
        postId: this.postId,
        secondsAgo: 0,
      };

      postService
        .submitComment(theComment)
        .then((response) => {
          if (response.status === 201) {
            this.$store.commit("ADD_COMMENT", theComment);
            this.newComment = "";
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    deleteComment(comment) {
      if (comment.commentId) {
        postService
          .deleteComment(comment.commentId)
          .then((response) => {
            if (response.status === 204) {
              this.$store.commit("REMOVE_COMMENT", comment);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert(
          "Sorry, we cannot do that. Please refresh the page before deleting a new comment"
        );
      }
    },
    timeSince(comment) {
      let seconds = comment.secondsAgo;
      if (seconds === 0) {
        return "Just Now";
      }

      var interval = seconds / 31536000;

      if (interval > 1) {
        return Math.floor(interval) + " years ago";
      }
      interval = seconds / 2592000;
      if (interval > 1) {
        return Math.floor(interval) + " months ago";
      }
      interval = seconds / 86400;
      if (interval > 1) {
        return Math.floor(interval) + " days ago";
      }
      interval = seconds / 3600;
      if (interval > 1) {
        return Math.floor(interval) + " hours ago";
      }
      interval = seconds / 60;
      if (interval > 1) {
        return Math.floor(interval) + " minutes ago";
      }
      return Math.floor(seconds) + " seconds ago";
    },
  },
};
</script>


<style scoped>
.comment-input,
.comment p,
.submit-btn {
  font-size: 1rem;
  font-family: "Spartan", "Arial", sans-serif;
}

.username {
  font-weight: bold;
}

.comment-form {
  padding: 1rem 0rem;
  display: grid;
  gap: 3px;
  grid-template-columns: 7fr 1fr;
}
.comment-form .comment-input {
  margin: 0;
  padding: 0.5rem;
  resize: none;
}

.submit-btn {
  height: 38px;
}

.comment-wrapper {
  max-height: 10rem;
  overflow-y: scroll;
}

.comment {
  display: flex;
  justify-content: space-between;
  padding: 0.5rem;
}

.comment p {
  margin: 0;
}

.comment:nth-of-type(odd) {
  background-color: lightblue;
}

.comment:nth-of-type(even) {
  background-color: rgb(139, 176, 189);
}

#time-since {
  font-size: 0.75rem;
}

#delete-comment-btn:hover {
  color: red;
}
</style>
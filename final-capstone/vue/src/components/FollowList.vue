<template>
  <div id="follow-list">
    <h3 id="follow-header">Following</h3>
    <h3>-{{ numFollowed }}-</h3>
    <!-- display clickable list of users you follow -->
    <div id="follow-list-contents">
      <p
        class="follow-list-item"
        v-for="followed in $store.state.followList"
        v-bind:key="followed.user_id"
        v-on:click="$router.push(`/profile/${followed.username}`)"
      >
        <!-- replaced {followed.user_id} on line 13 with followed.username-->
        {{ followed.username }}
      </p>
    </div>
  </div>
</template>

<script>
import followService from "../services/FollowService.js";

export default {
  created() {
    followService
      .list()
      .then((response) => {
        this.$store.commit("SET_FOLLOW_LIST", response.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
  },
  methods: {},
  computed: {
    numFollowed() {
      return this.$store.state.followList.length;
    },
  },
};
</script>

<style>
#follow-list {
  height: 20rem;
  width: 10rem;
  background-color: rgb(241, 248, 243);
  margin-top: 2rem;
  margin-left: 0.5rem;
  position: fixed;
  text-align: center;
  border: rgb(241, 248, 243);
  border-radius: 15px;
  padding: 0.5rem;
}

#follow-header {
  font-size: 1.2rem;
  margin: 1.3rem 0;
}

#follow-list-contents p {
  font-size: 1rem;
  padding: 0.5rem;
  margin: 0;
}

#follow-list-contents p:nth-of-type(even) {
  background-color: rgba(66, 185, 201, 0.3);
}

#follow-list-contents p:nth-of-type(odd) {
  background-color: rgba(110, 220, 253, 0.3);
}

.follow-list-item p {
  text-align: center;
}
</style>
<template>
  <div id="profile">
    <div id="profile-body">
      <div id="profile-photo">
        <img :src="$store.state.profile.profileImage" />
      </div>
      <div id="username">
        <h2 id="username">{{ $store.state.profile.username }}</h2>
      </div>
      <button
        class="follow-btn bluehover"
        v-on:click="followUser"
        v-if="!followed"
      >
        Follow
      </button>
      <button class="follow-btn redhover" v-on:click="unfollowUser" v-else>
        Unfollow
      </button>
      <div id="posts-follow">
        <ul>
          <li id="posts-number" style="padding-left: 2em">
            {{ $store.state.profile.numPosts }} Posts
          </li>
          <li id="followers-number" style="padding-left: 2em">
            {{ $store.state.profile.numFollowers }} Followers
          </li>
          <li id="following-number" style="padding-left: 2em">
            {{ $store.state.profile.numFollowing }} Following
          </li>
        </ul>
      </div>
      <div id="actual-name">
        <h2 id="profile-name">{{ $store.state.profile.profileName }}</h2>
        <p id="about">{{ $store.state.profile.description }}</p>
      </div>
    </div>
    <hr />
    <h3>{{ $store.state.profile.profileName }}'s Posts</h3>
    <hr />
    <div id="post-feed">
      <profile-post-feed :profileUsername="$store.state.profile.username" />
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService.js";
import followService from "../services/FollowService.js";
import ProfilePostFeed from "../components/ProfilePostFeed.vue";

export default {
  components: {
    ProfilePostFeed,
  },
  created() {
    profileService
      .getProfileByName(this.$route.params.username)
      .then((response) => {
        this.$store.commit("SET_PROFILE", response.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
  },
  methods: {
    followUser() {
      followService.follow(this.$store.state.profile.userId);
      this.$store.commit("TOGGLE_FOLLOW_PROFILE");
    },
    unfollowUser() {
      followService.unfollow(this.$store.state.profile.userId);
      this.$store.commit("TOGGLE_FOLLOW_PROFILE");
    },
  },
  computed: {
    followed() {
      return this.$store.state.profile.followed;
    },
  },
};
</script>

<style>
#profile {
  margin: 10px;
}

#profile-body {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-gap: 20px;
  grid-template-areas:
    "profile-photo username follow-btn"
    "profile-photo posts-follow ."
    ". actual-name .";
}

#profile-photo img {
  grid-area: profile-photo;
  border-radius: 50%;
  height: 8rem;
  width: 8rem;
  margin-left: 25%;
}
#username {
  grid-area: username;
}

.follow-btn {
  grid-area: follow-btn;
  height: 2rem;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 5px;
  width: 9rem;
}

#posts-follow {
  grid-area: posts-follow;
}
#actual-name {
  grid-area: actual-name;
}

li {
  display: inline;
}

.redhover:hover {
  background-color: rgb(190, 188, 188);
}
</style>
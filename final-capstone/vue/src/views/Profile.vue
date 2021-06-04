<template>
  <div id="your-profile">
    <div id="your-profile-body">
      <div id="your-profile-photo">
        <img :src="$store.state.yourProfile.profileImage" />
      </div>
      <div id="username-box">
        <h2 id="username">{{ $store.state.yourProfile.username }}</h2>
      </div>
      <div id="make-button">
        <button
          id="make"
          class="bluehover"
          v-on:click="$router.push('/upload-photo')"
        >
          Make Post
          <i class="fas fa-images"></i>
        </button>
      </div>
      <div id="posts-follow">
        <ul>
          <li id="posts-number">
            {{ $store.state.yourProfile.numPosts }} Posts
          </li>
          <li id="followers-number" style="padding-left: 2em">
            {{ $store.state.yourProfile.numFollowers }} Followers
          </li>
          <li id="following-number" style="padding-left: 2em">
            {{ $store.state.yourProfile.numFollowing }} Following
          </li>
        </ul>
      </div>
      <div id="actual-name">
        <h2 id="your-profile-name">
          {{ $store.state.yourProfile.profileName }}
        </h2>
        <p id="about">{{ $store.state.yourProfile.description }}</p>
      </div>
    </div>
    <hr />
    <h2>Your Posts</h2>
    <hr />
    <div id="post-feed">
      <profile-post-feed :profileUsername="$store.state.yourProfile.username" />
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService.js";
import ProfilePostFeed from "../components/ProfilePostFeed.vue";

export default {
  components: {
    ProfilePostFeed,
  },

  created() {
    profileService
      .getMyProfile()
      .then((response) => {
        this.$store.commit("SET_YOUR_PROFILE", response.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
  },
};
</script>

<style >
#your-profile {
  margin: 10px;
}

#your-profile-body {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-gap: 20px;
  grid-template-areas:
    "profile-photo username make-button"
    "profile-photo posts-follow ."
    ". actual-name .";
}

#your-profile-photo img {
  grid-area: profile-photo;
}
#your-profile-photo img {
  border-radius: 50%;
  height: 8rem;
  width: 8rem;
  margin-left: 25%;
}

#your-post-feed {
  margin-left: 20px;
  margin-right: 20px;
}

#username-box {
  grid-area: username;
  display: flex;
}

/* #username {
  align-self: center;
} */

#make-button {
  grid-area: make-button;
}
#posts-follow {
  grid-area: posts-follow;
}

#posts-follow ul {
  padding: 0;
}

#actual-name {
  grid-area: actual-name;
}
li {
  display: inline;
}

#make {
  font-size: 0.98rem;
  background-color: rgb(239, 253, 253);
  width: 120px;
  height: 40px;
  border-radius: 6px;
  border-style: solid;
  border-width: thin;
  border-color: cadetblue;
}
</style>
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    posts: [],
    token: currentToken || '',
    user: currentUser || {},
    yourProfile: {},
    profile: {},
    followList: [],
    post: {}
  },
  mutations: {
    ADD_COMMENT(state, comment) {
      console.log(comment)
      const postToChange = state.posts.findIndex(post => post.postId === comment.postId);
      state.posts[postToChange].comments.push(comment);
    },
    REMOVE_COMMENT(state, commentToDelete) {
      const postIndex = state.posts.findIndex(post => post.postId === commentToDelete.postId);
      const postToChange = state.posts[postIndex]
      postToChange.comments = postToChange.comments.filter(comment => comment.commentId !== commentToDelete.commentId)
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_POSTS(state, data) {
      state.posts = data;
    },
    SET_POST(state, data) {
      state.post = data;
    },
    TOGGLE_LIKE(state, postToChange) {
      const postToModify = state.posts.findIndex((post) => post.postId === postToChange.postId)
      postToChange.liked = !postToChange.liked;
      if (postToChange.liked) {
        postToChange.likeNumber++;
      } else {
        postToChange.likeNumber--;
      }
      state.posts[postToModify] = postToChange;
    },
    TOGGLE_FAVORITE(state, postToChange) {
      const postToModify = state.posts.findIndex((post) => post.postId === postToChange.postId)
      postToChange.favorited = !postToChange.favorited;
      state.posts[postToModify] = postToChange;
    },
    SET_YOUR_PROFILE(state, data) {
      state.yourProfile = data;
    },
    SET_PROFILE(state, data) {
      state.profile = data;
    },
    SET_FOLLOW_LIST(state, data) {
      state.followList = data;
    },
    TOGGLE_FOLLOW_PROFILE(state) {
      state.profile.followed = !state.profile.followed;
    }
  },
})

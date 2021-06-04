import axios from 'axios';


export default {

    submitComment(comment) {
        return axios.post(`/comments`, comment);
    },
    deleteComment(commentId) {
        return axios.delete(`/comments/${commentId}`);
    },
    listPosts() {
        return axios.get('/posts')
    },
    getPost(postId) {
        return axios.get(`/posts/${postId}`)
    },
    addPost(thePost) {
        return axios.post('/posts', thePost)
    },
    listFavorites() {
        return axios.get('/favorites')
    },
    addFavorite(postId) {
        return axios.post(`/favorites/${postId}`)
    },
    removeFavorite(postId) {
        return axios.delete(`/favorites/${postId}`)
    },
    addLiked(postId) {
        return axios.post(`/liked/${postId}`)
    },
    removeLiked(postId) {
        return axios.delete(`/liked/${postId}`)
    },
    listFollowing() {
        return axios.get(`/following`)
    }


}
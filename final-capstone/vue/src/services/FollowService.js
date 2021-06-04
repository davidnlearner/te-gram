import axios from 'axios';


export default {
    list() {
        return axios.get('/follow')
    },
    follow(followedId) {
        return axios.post(`/follow/${followedId}`)
    },
    unfollow(followedId) {
        return axios.delete(`/follow/${followedId}`)
    }

}
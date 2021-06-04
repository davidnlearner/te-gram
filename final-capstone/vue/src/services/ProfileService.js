import axios from 'axios';

export default {
    getMyProfile() {
        return axios.get('/profiles')
    },
    getProfileByName(username) {
        return axios.get(`/profiles/${username}`)
    },
    updateProfile() {
        return axios.put('/profiles')
    }

}
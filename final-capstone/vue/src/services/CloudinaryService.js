const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/tegram2021/image/upload';
const CLOUDINARY_UPLOAD_PRESET = 'eapkuwqm';



export default {
    upload(formData) {
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);

        return fetch(CLOUDINARY_URL, {
            method: 'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            body: formData
        });
    }

}
const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/tegram2021/image/upload';

export default {
    upload(formData) {
        formData.append('upload_preset', process.env.CLOUDINARY_UPLOAD_PRESET);

        return fetch(CLOUDINARY_URL, {
            method: 'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            body: formData
        });
    }

}
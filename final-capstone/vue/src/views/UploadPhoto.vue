<template>
  <div id="upload-photo">
    <form
      id="upload-form"
      autocomplete="off"
      v-on:submit.prevent="uploadImages"
    >
      <br />
      <input
        id="imageInput"
        type="file"
        multiple
        v-on:change="filePicked($event)"
      />
      <ul id="preview"></ul>
      <p></p>
      <label for="caption">Caption: </label>
      <input id="caption" name="caption" type="text" v-model="post.caption" />
      <p></p>
      <input id="submitButton" type="submit" />
    </form>
  </div>
</template>

<script>
import postService from "../services/PostService";
import cloudinaryService from "../services/CloudinaryService";

export default {
  data() {
    return {
      post: {
        caption: "",
        images: [],
      },
      data_urls: [],
      ready: false,
    };
  },
  methods: {
    checkImageStatus(evt) {
      const files = evt.target.files;

      for (let i = 0; i < files.length; i++) {
        let fileName = files[i];

        const reader = new FileReader();
        reader.readAsDataURL(fileName);
        reader.onload = () => {
          this.data_urls.push(reader.result);
          this.ready = true;
        };
      }
    },
    async addPost() {
      postService
        .addPost(this.post)
        .then(() => {
          console.log(this.post.data_urls);
          this.$router.push("/");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    uploadImages() {
      let checks = 0;
      for (let index = 0; index < this.data_urls.length; index++) {
        const formData = new FormData();
        formData.append("file", this.data_urls[index]);
        cloudinaryService
          .upload(formData)
          .then((response) => response.json())
          .then((jsonData) => {
            this.post.images.push(String(jsonData.url));
            checks++;
            if (checks === this.data_urls.length) {
              this.addPost();
            }
          })
          .catch((error) => {
            console.log(error.response);
          });
      }
    },
    showFile() {
      var preview = document.getElementById("preview");
      var fileInput = document.getElementById("imageInput");

      if (fileInput != null) {
        //cycle through imported images
        for (var i = 0; i < fileInput.files.length; i++) {
          var reader = new FileReader();
          reader.onload = function (readerEvent) {
            var listItem = document.createElement("li");
            listItem.innerHTML =
              "<img src='" + readerEvent.target.result + "' />"; // creates img tag for display
            preview.append(listItem); // adds img to preview list
          };

          reader.readAsDataURL(fileInput.files[i]);
        }
      }
    },
    filePicked(evt) {
      this.showFile();
      this.checkImageStatus(evt);
    },
  },
};
</script>

<style>
#upload-photo {
  text-align: center;
}
#imageInput {
  display: block;
  margin: auto;
}
#caption {
  display: block;
  width: 20rem;
  margin: auto;
}
#submitButton {
  display: block;
  margin: auto;
}
</style>
 
 
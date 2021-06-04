<template>
  <div id="app">
    <banner />
    <router-view />
    <link
      href="https://fonts.googleapis.com/css2?family=Spartan&display=swap"
      rel="stylesheet"
    />
  </div>
</template>



<script>
import Banner from "./components/Banner.vue";
export default {
  components: { Banner },
};
</script>

<style>
body {
  margin: 0;
  font-family: "Spartan", "Arial", sans-serif;
}
</style>

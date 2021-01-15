<template lang="html">
  <div>
    <input type="text" class="search-bar" @input="newList" />
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    name: SearchBar;
  },
  methods: {
    newListfunction(event) {
      this.$emit("newSearch", event.target.value);
    },
  },
};
</script>

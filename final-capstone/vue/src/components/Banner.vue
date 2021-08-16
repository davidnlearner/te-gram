<template>
    <div id="banner">
        <div id="title">
            <img class="logo" src="../Assets/TE-gram-logo.png" alt="" />

            <!--
      <link rel="preconnect" href="https://fonts.gstatic.com" />
      <link
        href="https://fonts.googleapis.com/css2?family=Pattaya&display=swap"
        rel="stylesheet"
      />
      <h1>TE-GRAM</h1>
      -->

            <img
                class="logo-text"
                src="../Assets/TE-GRAM actual white.svg"
                alt=""
            />
        </div>
        <!-- 
    <form id="searchbar">
      <input type="search" id="query" name="q" placeholder="Search..." />
    </form> -->

        <div class="nav" v-if="this.windowWidth > 1024">
            <router-link
                id="homeLink"
                v-bind:to="{ name: 'home' }"
                v-if="$store.state.token != ''"
                >Home &nbsp;/&nbsp;
            </router-link>

            <router-link
                id="favoritesLink"
                v-bind:to="{ name: 'favorites' }"
                v-if="$store.state.token != ''"
                >Favorites &nbsp;/&nbsp;
            </router-link>

            <router-link
                id="profileLink"
                v-bind:to="{ name: 'profile' }"
                v-if="$store.state.token != ''"
                >Profile &nbsp;/&nbsp;
            </router-link>

            <router-link
                id="logoutLink"
                v-bind:to="{ name: 'logout' }"
                v-if="$store.state.token != ''"
                >Logout</router-link
            >
        </div>
        <div class="nav dropdown" v-else>
            <button class="dropbtn">
                <i class="fas fa-bars" id="fas-fa-bars"></i>
            </button>
            <div class="dropdown-content">
                <ul class="nav-dropdown-list">
                    <router-link
                        tag="li"
                        v-bind:to="{ name: 'home' }"
                        v-if="$store.state.token != ''"
                        >Home</router-link
                    >
                    <router-link
                        tag="li"
                        v-bind:to="{ name: 'favorites' }"
                        v-if="$store.state.token != ''"
                        >Favorites</router-link
                    >
                    <router-link
                        tag="li"
                        v-bind:to="{ name: 'profile' }"
                        v-if="$store.state.token != ''"
                        >Profile</router-link
                    >
                    <router-link
                        tag="li"
                        v-bind:to="{ name: 'logout' }"
                        v-if="$store.state.token != ''"
                        >Logout</router-link
                    >
                </ul>
            </div>
        </div>
    </div>
</template>

<script scoped>
export default {
    data() {
        return {
            windowWidth: window.innerWidth,
            txt: "",
            search: "",
        };
    },
    watch: {
        windowWidth(newWidth, oldWidth) {
            this.txt = `it changed to ${newWidth} from ${oldWidth}`;
        },
    },

    mounted() {
        this.$nextTick(() => {
            window.addEventListener("resize", this.onResize);
        });
    },

    beforeDestroy() {
        window.removeEventListener("resize", this.onResize);
    },

    methods: {
        onResize() {
            this.windowWidth = window.innerWidth;
        },
    },
};
</script>

<style>
#banner {
    background-color: #00adee;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: "logo . nav";
    width: 100%;
    margin: 0;
}

.title {
    grid-area: logo;
    justify-items: left;
}

.nav {
    grid-area: nav;
    justify-items: right;
}

#fas-fa-bars {
    font-size: 2rem;
    background-color: #00adee;
}

@keyframes fadeIn {
    from {
        opacity: 1;
    }

    to {
        opacity: 0.1;
    }
}

@keyframes fadeOut {
    from {
        opacity: 0.1;
    }

    to {
        opacity: 1;
    }
}

#title {
    display: flex;
    align-self: center;
}

#title h1 {
    margin-left: 0.5rem;
    font-family: "Pattaya", sans-serif;
}

.logo {
    height: 5rem;
    margin-left: auto;
}

.logo-text {
    height: 6rem;
}

/* #searchbar {
  display: inline;
  align-self: center;
} */

#query {
    border-radius: 3px 3px 3px 3px;
    border: white;
    font-size: 1.3rem;
}

.nav {
    display: inline;
    align-self: center;
}

#homeLink {
    text-decoration: none;
    font-size: 1.2rem;
    color: aliceblue;
    font-weight: bold;
}
#favoritesLink {
    text-decoration: none;
    font-size: 1.2rem;
    color: aliceblue;
    font-weight: bold;
}
#profileLink {
    text-decoration: none;
    font-size: 1.2rem;
    color: aliceblue;
    font-weight: bold;
}
#logoutLink {
    text-decoration: none;
    font-size: 1.2rem;
    color: aliceblue;
    font-weight: bold;
}

.dropdown {
    position: relative;
    display: inline-block;
    justify-self: right;
    padding-right: 1.5rem;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 100px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    padding: 2px 2px;
    z-index: 1;
}

.dropdown-content li {
    display: block;
}

.dropdown:hover .dropdown-content {
    display: block;
}

li {
    list-style: none;
    justify-self: center;
    margin: 0.2rem;
    cursor: pointer;
}

.nav-dropdown-list {
    margin: 0;
    padding: 0.5rem;
}

@media only screen and (max-width: 1024px) {
    #title h1 {
        font-size: 1.1rem;
    }
    #searchbar input {
        width: 5rem;
    }

    .dropbtn {
        align-self: center;
        margin: 0;
        background-color: #00adee;
        color: white;
    }
}
</style>

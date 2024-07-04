<template>

  <div class="header">
    <ul>
      <li><img alt="Vue logo" src="./assets/logo.png"></li>
      <li><router-link :to="{ name: 'SeatHome' }">Home</router-link></li>
      <li><router-link :to="{ name: 'AddSeat' }">新增座位</router-link></li>
      <li><router-link :to="{ name: 'AddEmployee' }">新增員工</router-link></li>
    </ul>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>

  <router-view></router-view>
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.header{
  width: 100%;
  display: inline-block;
}
.header li{
  float: left;
  margin: 10px;
  padding : 10px;
  list-style:none;
  font-size: 25px;
  font-weight: bold;
  text-decoration: none;
}
.header li a{
  text-decoration: none;
  color: #006000;
}
.header li img{
  width: 60px;
}
.header ul{
  float: left;
  display: inline-block;
}
</style>

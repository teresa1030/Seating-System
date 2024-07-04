<template>
  <h1>增加座位</h1>
  <div class="content">
    <div class="addSeat">
      <form @submit.prevent="addSeat">
        <label for="title">樓層編號：</label>
        <input type="number" id="floorNo" v-model="newSeat.floorNo" required>

        <label for="author">座位編號：</label>
        <input type="number" id="seatNo" v-model="newSeat.seatNo" required>

        <button type="submit">新增座位</button>
      </form>
    </div>

  </div>

</template>
<script>
export default {
  name: 'AddSeat',
  data(){
    return {
      newSeat: {
        floorNo:'',
        seatNo:''
      },
    }
  },
  methods:{
    addSeat(){
      fetch('/api/seats', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.newSeat)
      })
          .then(response =>{
            console.log('Status code:', response.status);
            if(!response.ok){
              alert("新增失敗");
              throw new Error(response.status);
            }
            return response.json()
          })
          .then(data => {
            alert("新增成功");
            console.log('新增成功：', data);
            this.$router.push('/SeatHome');
          })
          .catch(error => {
            alert("新增失敗");
            console.error('新增失败：', error);
          });
    },
  }

}
</script>

<style scoped>
/* 组件样式 */
.addSeat {
  max-width: 300px;
  margin: 0 auto;
}
form {
  display: flex;
  flex-direction: column;
}
label {
  margin-bottom: 0.5rem;
}
input, button {
  margin-bottom: 1rem;
  padding: 0.5rem;
}
button {
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  margin-top: 50px;
  width: 150px;
  margin-left:70px;
  border-radius: 6px;
  font-weight: bold;
  padding : 5px;
}
button:hover {
  opacity: 0.8;
}
</style>
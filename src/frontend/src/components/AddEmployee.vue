<template>
  <h1>增加員工</h1>
  <div class="content">
    <div class="addEmployee">
      <form @submit.prevent="addEmployee">
        <label for="title">員工編號：</label>
        <input type="number" id="userId" v-model="newEmployee.userId" required>

        <label for="author">姓名：</label>
        <input type="text" id="name" v-model="newEmployee.name" required>

        <label for="author">Email：</label>
        <input type="email" id="email" v-model="newEmployee.email" required>
        <div class="chooseSeat">
          <div v-for="floor in uniqueFloors" :key="floor">
            <div class="floorSeats">
              <div class="seatStyle">
                <ul >

                  <li v-for="seat in filteredSeats(floor)" :key="seat.seatId"
                      :class="{ 'has-employee': findEmployeeSeat(seat.seatId), 'selected': seat.seatId === selectedSeat  }"
                      @click="findEmployeeSeat(seat.seatId) ? null : selectSeat(seat.seatId)">
                    {{floor}}樓：座位 {{ seat.seatNo }} {{findEmployeeSeat(seat.seatId)}}
                  </li>
                </ul>
                <div class="clear"></div>
              </div>
            </div>
          </div>

        </div>
        <div class="options">
          <span class="vacant">空位</span>
          <span class="occupy">已佔用</span>
          <span class="choose">請選擇</span>
        </div>
        <div class="clear"></div>
        <button type="submit">新增員工</button>
      </form>
    </div>

  </div>

</template>
<script>
export default {
  name: 'AddEmployee',
  data(){
    return {
      newEmployee: {
        userId:'',
        name:'',
        email:'',
        seatId:''
      },
      seats: [],
      employees:[],
      selectedSeat: null,
      selectedEmployeeInfo: null,
    }
  },
  mounted() {
    fetch("/api/seats")
        .then((response) => response.json())
        .then((data) => {
          this.seats = data;
          console.log(data);
          console.log(this.seats[0].seatId);
        });
    fetch("/api/employees")
        .then((response) => response.json())
        .then((data) => {
          this.employees = data;
          console.log(data);
        });
  },
  computed: {
    uniqueFloors() {
      return [...new Set(this.seats.map(seat => seat.floorNo))];
    }
  },
  methods:{
    filteredSeats(floor) {
      return this.seats.filter(seat => seat.floorNo === floor);
    },
    findEmployeeSeat(seatId) {
      const employee = this.employees.find(employee => employee.seatId === seatId);
      // console.log(employee);
      if(employee && employee.userId){
        return "[員編 " + employee.userId+"]";
      }
    },
    selectSeat(seatId) {
      console.log(seatId)
      this.selectedSeat = seatId
      this.newEmployee.seatId = seatId
      this.employees.push(this.newEmployee)
      console.log(this.newEmployee)
    },
    addEmployee(){
      fetch('/api/employees', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.newEmployee)
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
.addEmployee {
  margin: 0 auto;
  justify-content: center;
  align-items: center;
}
form {
  display: flex;
  flex-direction: column;
}
label {
  margin-bottom: 0.5rem;
}
input {
  display: inline-block;
  width: 250px;
  margin: auto;
  padding: 0.5rem;
}
button {
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  width: 150px;
  border-radius: 6px;
  font-weight: bold;
  padding : 5px;
  margin: auto;
  margin-top: 50px;
}
button:hover {
  opacity: 0.8;
}
.content {
  justify-content: center;
  align-items: center;
}
.floorSeats {
  margin: auto;
}
.seatStyle ul{
  display: inline-block;
}
.seatStyle li {
  width: 250px;
  float: left;
  height: 40px;
  background-color: 	#D0D0D0;
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px;
  font-weight: bold;
  border-radius: 6px;
  passing:5 px;
  cursor: pointer;
}
.seatStyle .has-employee {
  background-color: #FF5151;
  cursor: none;
}
.options{
  display: inline-block;
  margin: auto;
}
.options span{
  float: left;
  width: 100px;
  height: 25px;
  border-radius: 6px;
  font-weight: bold;
  margin: 8px;
  padding : 5px;
}
.options .vacant{
  background-color: #D0D0D0;
}
.options .occupy{
  background-color: #FF5151;
}
.options .choose{
  background-color: #02C874;
}
.seatStyle .selected {
  background-color: #02C874;
}
</style>
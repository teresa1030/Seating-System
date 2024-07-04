<template>
  <div class="container">
    <div>
      <label for="employeeSelect">選擇員工：</label>
      <select id="employeeSelect" v-model="selectedEmployee">
        <option value="">請選擇</option>
        <option v-for="employee in employees" :key="employee.userId" :value="employee.userId">
          員編 {{ employee.userId }}
        </option>
      </select>
    </div>
    <div v-for="floor in uniqueFloors" :key="floor">
      <div class="floorSeats">
        <div class="seatStyle">
          <ul >

            <li v-for="seat in filteredSeats(floor)" :key="seat.seatId"
                :class="{ 'has-employee': findEmployeeSeat(seat.seatId), 'selected': seat.seatId === selectedSeat  }"
                @click="findEmployeeSeat(seat.seatId) ? null : selectSeat(seat.seatId)">
<!--                @click="findEmployeeSeat(seat.seatId) ? null : selectSeat(seat.seatId)">-->
              {{floor}}樓：座位 {{ seat.seatNo }} {{findEmployeeSeat(seat.seatId)}}
            </li>
          </ul>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <div class="options">
      <span class="vacant">空位</span>
      <span class="occupy">已佔用</span>
      <span class="choose">請選擇</span>
    </div>
    <div class="clear"></div>
    <button @click="updateSeat()">更新</button>
  </div>

<!--  </div>-->
</template>

<script>
export default {
  name: 'SeatHome',
  data(){
    return {
      seats: [],
      employees:[],
      selectedEmployee: null,
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
  methods: {
    filteredSeats(floor) {
      return this.seats.filter(seat => seat.floorNo === floor);
    },
    // findEmployeeSeat(seatId) {
    //   return this.employees
    //       .filter(employee => employee.seatId === seatId)
    //       .map(employee => employee.userId);
    // },
    findEmployeeSeat(seatId) {
      const employee = this.employees.find(employee => employee.seatId === seatId);
      // console.log(employee);
      if(employee && employee.userId){
        return "[員編 " + employee.userId+"]";
      }
    },
    selectSeat(seatId) {
      if (this.selectedEmployee) {
        const employee = this.employees.find(employee => employee.userId === this.selectedEmployee);
        employee.seatId = seatId
        this.selectedSeat = seatId
        console.log(employee)
        this.selectedEmployeeInfo = employee
        console.log(this.selectedEmployee)
        console.log(this.selectedSeat)
      } else {
        alert("請選擇一位員工");
      }
    },
    updateSeat(){
      fetch(`/api/employees/${this.selectedEmployee}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.selectedEmployeeInfo)
      })
          .then(response => {
            console.log('Status code:', response.status);
            if (!response.ok) {
              alert("修改失敗請再試一次");
              window.location.reload();
              throw new Error(response.status);

            }
            return response.json()
          })
          .then(data => {
            console.log('新增成功：', data);
            alert("修改成功");
            window.location.reload();
            //this.$router.push('/VoteEvent/'+data.voteId);
          })
          .catch(error => {
            console.error('Update Error:', error);
          });
    },
  }
}
</script>

<style>
.container {
  justify-content: center;
  align-items: center;
}
.floorSeats {
  margin: auto;
}
.seatStyle ul{
  display: inline-block;
}
.container select{
  width: 200px;
  height: 30px;
  border-radius: 6px;
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
  cursor: pointer;
}
.seatStyle .has-employee {
  background-color: #FF5151;
  cursor: none;
}
.options{
  display: inline-block;
  margin-bottom: 30px;
  margin-top: 70px;
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
.container button{
  width: 100px;
  border-radius: 6px;
  font-weight: bold;
  margin: 8px;
  padding : 5px;
  border:none;
  cursor: pointer;
}

</style>
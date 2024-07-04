import { createRouter, createWebHistory } from 'vue-router';
import HelloWorld from "@/components/HelloWorld.vue";
import SeatHome from "@/components/SeatHome.vue";
import AddSeat from "@/components/AddSeat.vue";
import AddEmployee from "@/components/AddEmployee.vue";



const routes = [
    {
        path: '/HelloWorld',
        name: 'HelloWorld',
        component: HelloWorld
    },
    {
        path: '/SeatHome',
        name: 'SeatHome',
        component: SeatHome
    },
    {
        path: '/AddSeat',
        name: 'AddSeat',
        component: AddSeat
    },
    {
        path: '/AddEmployee',
        name: 'AddEmployee',
        component: AddEmployee
    },

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
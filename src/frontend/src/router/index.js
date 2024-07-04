import { createRouter, createWebHistory } from 'vue-router';
import HelloWorld from "@/components/HelloWorld.vue";
import SeatHome from "@/components/SeatHome.vue";



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

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from "@/pages/LoginPage";
import CreateSchoolPage from "@/pages/CreateSchoolPage";
import HomePage from "@/pages/HomePage";

Vue.use(VueRouter)

const routes = [
    {
        path: "/login",
        name: "LoginPage",
        component: LoginPage
    },
    {
        path: "/",
        component: HomePage
    },
    {
        path: "/createSchool",
        component: CreateSchoolPage
    }
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

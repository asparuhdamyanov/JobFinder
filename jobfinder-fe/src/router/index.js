import Vue from 'vue'
import VueRouter from 'vue-router'
import Ad from '../views/Ad.vue'
import AdInfo from '../views/AdInfo.vue'
import AddAd from '../views/AddAd.vue'
import About from '../views/About.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'About',
    component: About
  },
  {
    path: '/ad',
    name: 'Ad',
    component: Ad
  },
  {
    path: '/adInfo',
    name: 'AdInfo',
    component: AdInfo
  },
  {
    path: '/AddAd',
    name: 'AddAd',
    component: AddAd
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router

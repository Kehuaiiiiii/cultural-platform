import Vue from 'vue'
import VueRouter from 'vue-router'

// 路由懒加载
const Login = () => import('../components/Login.vue')
const Home = () => import('../components/Home.vue')
const Welcome = () => import('../components/Welcome.vue')
const GoodsList = () => import('../components/goods/List.vue')
const GoodsSell = () => import('../components/goods/Sell.vue')
const GoodsManage = () => import('../components/goods/Manage.vue')
const OrderList = () => import('../components/order/List.vue')
const OrderSell = () => import('../components/order/Sell.vue')
const OrderSearch = () => import('../components/order/Search.vue')
const UserManage = () => import('../components/user/Manage')

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/home',
    component: Home,
    redirect: '/Welcome',
    children: [
      { path: '/home', component: Welcome },
      { path: '/goods', component: GoodsList },
      { path: '/goods/list', component: GoodsList },
      { path: '/goods/sell', component: GoodsSell },
      { path: '/goods/manage', component: GoodsManage },
      { path: '/order', component: OrderList },
      { path: '/order/list', component: OrderList },
      { path: '/order/sell', component: OrderSell },
      { path: '/order/search', component: OrderSearch },
      { path: '/user/manage', component: UserManage },
    ] }

]

const router = new VueRouter({
  mode: 'history',
  routes
})

// 挂载路由导航守卫,to表示将要访问的路径，from表示从哪里来，next是下一个要做的操作 next('/login')强制跳转login
router.beforeEach((to, from, next) => {
  // 访问登录页，放行
  if (to.path === '/login') return next()
  // 获取token
  const tokenStr = window.sessionStorage.getItem('token')
  // 没有token, 强制跳转到登录页
  if (!tokenStr) return next('/login')
  next()
})

export default router

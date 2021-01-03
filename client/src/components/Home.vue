<template>
  <el-container>
    <!-- 头部 -->
    <el-header>
      <div class="head">
        <img src="../assets/logo.png" alt/>
        <span>南京文创平台-订单管理系统</span>
      </div>
      <div>
        <span class="welcome">欢迎回来，{{this.nickname}}</span>
        <el-button type="info" @click="logout">退出</el-button>
      </div>
    </el-header>
    <!-- 主体 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="togleCollapse">|||</div>
        <el-menu :collapse="isCollapse" :collapse-transition="false" router :default-active="activePath"
                 background-color="#333744" text-color="#fff" active-text-color="#409FFF">
          <!-- :collapse-transition="false" -> 关闭动画 -->
          <!-- router -> 导航开启路由模式 -->
          <el-menu-item index="/home">
            <i class="iconfont el-icon-s-home"></i>
            <span slot="title">Welcome</span>
          </el-menu-item>
          <!-- 一级菜单  -->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <!-- 一级菜单的模板区域 -->
            <template slot="title">
              <i :class="iconObj[item.id]"></i>
              <span>{{ item.name }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id"
                          @click="saveNavState('/' + subItem.path)">
              <!-- 导航开启路由模式：
                将index值作为导航路由 -->
              <!-- 二级菜单的模板区域 -->
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{ subItem.name }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 内容主体 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      // 左侧菜单数据
      menuList: [],
      iconObj: {
        // 商品管理
        '100': 'iconfont iconshangpin',
        // 订单管理
        '200': 'iconfont icondanju',
        // 用户管理
        '300': 'iconfont iconusers',

        // 其他
        '101': 'iconfont el-icon-menu',
        '102': 'iconfont el-icon-menu',
        '103': 'iconfont el-icon-menu',
        '104': 'iconfont el-icon-menu',
        '201': 'iconfont el-icon-menu',
        '202': 'iconfont el-icon-menu',
        '301': 'iconfont el-icon-menu',
      },
      // 默认不折叠
      isCollapse: false,
      // 被激活导航地址
      activePath: ''
    }
  },
  created() {
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
    this.nickname = window.sessionStorage.getItem('nickname')
    this.$notify({
      title: '欢迎回来，' + this.nickname,
      type: 'success'
    });
  },
  methods: {
    logout() {
      // 清空token
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    // 获取请求菜单
    async getMenuList() {
      const {data: res} = await this.$http.get('user/getMenu')
      if (res.code !== 200) return this.$message.error(res.msg)
      this.menuList = res.data.menuList
    },
    // 菜单的折叠与展开
    togleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // 保存连接的激活地址
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath)
    }
  }
}
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
}

.el-header {
  background-color: #373f41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
}

.head {
  display: flex;
  align-items: center;
}

.welcome {
  margin-right: 50px;
}

img {
  margin-left: 10px;
  height: 40px;
}

span {
  margin-left: 15px;
}

.el-aside {
  background-color: #333744;
}

.el-menu {
  border: none;
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>

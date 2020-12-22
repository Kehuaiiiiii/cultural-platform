<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <!-- 头像区域 -->
        <img src="../assets/logo.png" alt />
      </div>
      <!-- 登录表单区域 -->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont iconuser"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" prefix-icon="iconfont iconi-pwd"></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script>
export default {
  data(){
    return {
      // 登陆表单的数据绑定对象
      loginForm: {
        username:'admin',
        password:'123456',
      },
      // 这是表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username:[
          { required:true, message:"请输入登录用户名", trigger:"blur" },
          { min:3, max:10, message:"长度在3到10个字符中间", trigger:"blur" }
        ],
        // 验证密码是否合法
        password:[
          { required:true, message:"请输入登录密码", trigger:"blur" },
          { min:6, max:15, message:"长度在6到15个字符中间", trigger:"blur" }
        ],
      }
    }
  },
  methods: {
    // 重置
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    // 登录预验证 valid是验证结果 判断验证通过/失败
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if(!valid) return;
        // 发送请求
        const {data: res} = await this.$http.post('login',this.loginForm);
        if(res.meta.status!==200) return this.$message.error('登陆失败');
        this.$message.success('登陆成功')
        // 保存token
        window.sessionStorage.setItem('token', res.data.token);
        // 跳转到/home
        this.$router.push('/home')
      });
    }
  }
};
</script>

<style lang="less" scoped>
.login_container {
  height: 100%;
  background-image: url('../assets/bg.jpg');
  background-repeat: no-repeat;
  background-size:100% 100%;
  background-color: #fff;
}
.login_box {
  width: 450px;
  height: 280px;
  background-color: #fff;
  box-shadow: 0 0 20px #555;
  border-radius: 30px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  opacity: 0.95;
  .avatar_box {
    height: 130px;
    width: 130px;
    border-radius: 80%;
    padding: 10px;

    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background: rgba(255, 255, 255, 0);
    img {
      width: 100%;
      height: 100%;
      background-color: #eee;
    }
  }
}
.login_form {
  position: absolute;
  bottom: 0px;
  width: 100%;
  padding: 0px 20px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
</style>
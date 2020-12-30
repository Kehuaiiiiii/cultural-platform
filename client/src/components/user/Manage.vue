<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <div class="query">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input placeholder="请输入用户名" v-model="queryInfo.name" clearable @clear="getUserList">
              <el-button slot="append" icon="el-icon-search" @click="queryInfo.pagenum=1;getUserList()"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="userDialogVisible = true">添加用户</el-button>
          </el-col>
        </el-row>
      </div>

      <!-- 表格数据 -->
      <el-table class="table" :data="userList" border stripe highlight-current-row>
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="uid" prop="uid" width="200px" sortable></el-table-column>
        <el-table-column label="用户名" prop="username" sortable></el-table-column>
        <el-table-column label="昵称" prop="nickname" width="300px" sortable></el-table-column>
        <el-table-column label="用户类型" prop="rid" :formatter="ridFormat" width="140px" sortable></el-table-column>
        <el-table-column label="操作" width="140px">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="删除" placement="top">
              <el-button type="danger" icon="el-icon-delete" size="small" @click="deleteUser(scope.row.uid)"
                         circle></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
        <template slot="empty">
          <div>暂无数据</div>
        </template>
      </el-table>
    </el-card>
    <!-- 分页区域 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pagenum"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      background
    ></el-pagination>

    <!-- 添加用户 -->
    <el-dialog title="添加用户" :visible.sync="userDialogVisible" width="20%" @close="userDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="userForm"
        ref="userFormRef"
        :rules="userFormRules"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username" placeholder="仅支持英文、数字和下划线" oninput="value=value.replace(/[^\w]/g,'')"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" placeholder="仅支持英文、数字和下划线" oninput="value=value.replace(/[^\w]/g,'')"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="userForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="角色类型" prop="rid">
          <el-select v-model="userForm.rid" placeholder="请选择">
            <el-option
              v-for="item in role"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="userDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTabsName: 'manage',
      queryInfo: {
        name: '',
        pagenum: 1,
        pagesize: 5
      },
      totol: 0,
      ridMap: {
        1: '管理员',
        2: '卖家',
        3: '普通用户',
      },
      // 用户列表
      userList: [],
      // 添加用户对话框
      userDialogVisible: false,
      // 添加用户表单
      userForm: {
        username: '',
        password: '',
        nickname: '',
        rid: '',
      },
      role: [{
        value: '1',
        label: '管理员'
      }, {
        value: '2',
        label: '卖家'
      }, {
        value: '3',
        label: '普通用户'
      }],
      userFormRules: {
        // 验证用户名是否合法
        username: [
          {required: true, message: "请输入登录用户名", trigger: "blur"},
          {min: 3, max: 15, message: "长度在3到15个字符中间", trigger: "blur"}
        ],
        // 验证密码是否合法
        password: [
          {required: true, message: "请输入登录密码", trigger: "blur"},
          {min: 6, max: 15, message: "长度在6到15个字符中间", trigger: "blur"}
        ],
        nickname: [
          {required: true, message: "请输入昵称", trigger: "blur"},
        ]
      }
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    async getUserList() {
      const { data: res } = await this.$http.get('user/getUserInfo', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取用户列表失败！ 原因: ' + res.msg)
      }
      this.userList = res.data.userList
      this.total = res.data.total
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    handleCurrentChange(newSize) {
      this.queryInfo.pagenum = newSize
      this.getUserList()
    },
    ridFormat(row, column, cellValue, index) {
      return this.ridMap[cellValue]
    },
    userDialogClosed() {
      this.userDialogVisible = false
      this.$refs.userFormRef.resetFields()
    },
    async deleteUser(uid) {
      console.log(uid)
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 发送请求
        const {data: res} = this.$http.get('user/deleteUser', {params: uid});
        if (res.code !== 200) return this.$message.error('删除失败 原因: ' + res.msg);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getUserList()
      });
    },
    async addUser() {
      this.$refs.userFormRef.validate(async valid => {
        if (!valid) return;
        // 发送请求
        const {data: res} = await this.$http.get('user/addUser', {params: this.userForm});
        if (res.code !== 200) return this.$message.error('添加失败 原因: ' + res.msg);
        this.$message.success('添加成功')
        await this.getUserList()
        this.userDialogClosed()
      });
    }
  }
}
</script>

<style lang="less" scoped>
.query {
  margin: 15px 0px;
}
</style>

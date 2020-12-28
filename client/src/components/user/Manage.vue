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
              <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
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
        ref="userForm"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username" placeholder="仅支持英文、数字和下划线" oninput="value=value.replace(/[^\w]/g,'')"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" placeholder="仅支持英文、数字和下划线" oninput="value=value.replace(/[^\w]/g,'')"></el-input>
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
        pagesize: 10
      },
      totol: 0,
      ridMap: {
        1: '管理员',
        2: '卖家',
        3: '普通用户',
      },
      // 用户列表
      userList: [{
        uid: 101,
        username: 'abc',
        rid: 2,
      }, {
        uid: 2,
        username: 'abc',
        rid: 1,
      }],
      // 添加用户对话框
      userDialogVisible: false,
      // 添加用户表单
      userForm: {
        username: '',
        password: '',
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
      }]
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    getUserList() {

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
      this.$refs.userForm.resetFields()
    },
    async deleteUser(id) {
      console.log(id)
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getUserList()
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

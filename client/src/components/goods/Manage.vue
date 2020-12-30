<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <div class="query">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input placeholder="请输入内容" v-model="queryInfo.name" clearable @clear="getGoodsList">
              <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </div>

      <!-- tab页签区域 -->
      <el-tabs v-model="activeTabsName" @tab-click="handleTabsClick">
        <!-- 添加管理面板 -->
        <el-tab-pane label="商品管理" name="manage">
          <!-- 表格数据 -->
          <el-table class="table" :data="goodsList" border stripe highlight-current-row>
            <el-table-column type="index" :resizable="false"></el-table-column>
            <el-table-column label="商品名称" prop="name" :resizable="false"></el-table-column>
            <el-table-column label="商品价格(元)" prop="price" width="140px" sortable :resizable="false"></el-table-column>
            <el-table-column label="商品重量(kg)" prop="weight" width="140px" :resizable="false"></el-table-column>
            <el-table-column label="数量" prop="number" width="70px" :resizable="false"></el-table-column>
            <el-table-column label="上架时间" prop="created_time" width="100px" :resizable="false">
              <template slot-scope="scope">{{ scope.row.created_time | dateFormat }}</template>
            </el-table-column>
            <el-table-column label="操作" width="100px" :resizable="false">
              <template slot-scope="scope">
                <el-tooltip class="tooltip" effect="dark" content="查看详情" placement="top">
                  <el-button type="primary" icon="el-icon-info" size="small" @click="getGoodsInfo(scope.row.id)"
                             circle></el-button>
                </el-tooltip>
                <el-tooltip class="tooltip" effect="dark" content="下架" placement="top">
                  <el-button type="danger" icon="el-icon-delete" size="small" @click="deleteGoods(scope.row.id)"
                             circle></el-button>
                </el-tooltip>
              </template>
            </el-table-column>
            <template slot="empty">
              <div class="noGoods">暂无数据</div>
            </template>
          </el-table>
        </el-tab-pane>
        <!-- 添加审核面板 -->
        <el-tab-pane label="商品审核" name="check">
          <!-- 表格数据 -->
          <el-table class="table" :data="goodsList" border stripe highlight-current-row>
            <el-table-column type="index" :resizable="false"></el-table-column>
            <el-table-column label="商品名称" prop="name" :resizable="false"></el-table-column>
            <el-table-column label="商品价格(元)" prop="price" width="140px" sortable :resizable="false"></el-table-column>
            <el-table-column label="商品重量(kg)" prop="weight" width="140px" :resizable="false"></el-table-column>
            <el-table-column label="数量" prop="number" width="70px" :resizable="false"></el-table-column>
            <el-table-column label="上架时间" prop="created_time" width="100px" :resizable="false">
              <template slot-scope="scope">{{ scope.row.created_time | dataFormat }}</template>
            </el-table-column>
            <el-table-column label="操作" width="100px" :resizable="false">
              <template slot-scope="scope">
                <el-tooltip class="tooltip" effect="dark" content="查看详情" placement="top">
                  <el-button type="primary" icon="el-icon-info" size="small" @click="getGoodsInfo(scope.row.id)"
                             circle></el-button>
                </el-tooltip>
                <el-tooltip class="tooltip" effect="dark" content="通过" placement="top">
                  <el-button type="success" icon="el-icon-check" size="small" @click="updateGoodsUseful(scope.row.id)"
                             circle></el-button>
                </el-tooltip>
              </template>
            </el-table-column>
            <template slot="empty">
              <div class="noGoods">暂无数据</div>
            </template>
          </el-table>
        </el-tab-pane>
      </el-tabs>
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

    <!-- 商品详情 -->
    <el-dialog title="商品详情" :visible.sync="goodsDetailDialogVisible" width="50%" @close="goodsDetailDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="goodsDetailForm"
        ref="goodsDetailForm"
        label-width="100px"
      >
        <el-row :gutter="50">
          <el-col :span="16">
            <el-form-item label="商品名" prop="name">
              <el-input v-model="goodsDetailForm.name" readonly></el-input>
            </el-form-item>
            <el-form-item label="价格(元)" prop="price">
              <el-input-number v-model="goodsDetailForm.price" :min="0" :controls="false" :precision="2"
                               readonly></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="goodsDetailForm.number" oninput="value=value.replace(/[^0-9]/g,'')"
                        readonly></el-input>
            </el-form-item>
            <el-form-item label="重量(克)" prop="weight">
              <el-input v-model.number="goodsDetailForm.weight" oninput="value=value.replace(/[^0-9]/g,'')"
                        readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-image :src="goodsDetailForm.url" style="width:230px;height:230px;" alt="img"/>
          </el-col>
        </el-row>
        <el-form-item label="详细介绍" prop="introduce">
          <el-input v-model="goodsDetailForm.introduce" type="textarea" :rows="3" readonly></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="goodsDetailDialogClosed">确 定</el-button>
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
      // 商品列表
      goodsList: [],
      // 商品详情对话框
      goodsDetailDialogVisible: false,
      // 商品详情表单
      goodsDetailForm: {
        name: '',
        price: '',
        number: '',
        weight: '',
        introduce: '',
        url: '',
      },
    }
  },
  created() {
    // this.getCateList()
  },
  computed: {
    getTitleText() {
      if (this.activeTabsName === 'manage') {
        return '商品管理'
      }
      return '商品审核'
    }
  },
  methods: {
    // Tab页签点击事件的处理函数
    handleTabsClick() {
      this.getParamsData()
    },
    async getParamsData() {
      //   根据所选分类的Id,和当前面板的参数, 获取对应的参数
      // const { data: res } = await this.$http.get(
      //   `categories/${this.getCateId}/attributes`,
      //   {
      //     params: { sel: this.activeTabsName }
      //   }
      // )
      if (this.activeTabsName === 'manage') {
        // this.manyTableData = res.data
      } else {
        // this.onlyTableData = res.data
      }
    },
    // 根据分页获取对应的商品列表
    async getGoodsList () {
      const { data: res } = await this.$http.get('goods/getGoods', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取商品列表失败！ 原因: ' + res.msg)
      }
      this.goodsList = res.data.goods
      this.total = res.data.total
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
      this.getGoodsList()
    },
    handleCurrentChange(newSize) {
      this.queryInfo.pagenum = newSize
      this.getGoodsList()
    },
    goodsDetailDialogClosed() {
      this.goodsDetailDialogVisible = false
      this.$refs.goodsDetailForm.resetFields()
    },
    async getGoodsInfo(id) {
      this.goodsDetailDialogVisible = true
      console.log(id)
      let self = this
      this.goodsList.forEach(function (item) {
        if(item.id === id) {
          self.goodsDetailForm = item
          console.log(item)
          return
        }
      })
    },
    async deleteGoods(id) {
      console.log(id)
      this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const { data: res } = this.$http.get('goods/deleteGoods', id)
        if (res.code !== 200) {
          return this.$message.error('操作失败！ 原因: ' + res.msg)
        }
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getGoodsList()
      });
    },
    async updateGoodsUseful(id) {
      console.log(id)
    }
  },
  filters: {
    dateFormat: function(originVal) {
      const dt = new Date(originVal)

      const y = dt.getFullYear()
      const m = (dt.getMonth() + 1 + '').padStart(2, '0')
      const d = (dt.getDate() + '').padStart(2, '0')

      return `${y}-${m}-${d}`
    }
  }
}
</script>

<style lang="less" scoped>
.query {
  margin: 15px 0px;
}
</style>

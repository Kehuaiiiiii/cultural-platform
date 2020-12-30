<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>我的商品</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="queryInfo.name" clearable @clear="getSellingGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="getSellingGoodsList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加商品</el-button>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table class="table" :data="goodsList" border stripe highlight-current-row>
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="商品名称" prop="name" :resizable="false"></el-table-column>
        <el-table-column label="商品价格(元)" prop="price" width="140px" sortable :resizable="false"></el-table-column>
        <el-table-column label="商品重量(克)" prop="weight" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="数量" prop="number" width="70px" :resizable="false"></el-table-column>
        <el-table-column label="上架时间" prop="created_time" width="100px" :resizable="false">
          <template slot-scope="scope">{{ scope.row.created_time | dateFormat }}</template>
        </el-table-column>
        <el-table-column label="操作" width="100px" :resizable="false">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="查看详情" placement="top">
              <el-button type="primary" icon="el-icon-info" size="small" @click="goodsDetailDialogVisible=true;getGoodsInfo(scope.row.id)" circle></el-button>
            </el-tooltip>
            <el-tooltip class="tooltip" effect="dark" content="修改" placement="top">
              <el-button type="info" icon="el-icon-edit" size="small" @click="updateDialogVisible=true;updateGoodsInfo(scope.row.id)" circle></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
        <template slot="empty">
          <div class="noGoods">暂无数据</div>
        </template>
      </el-table>
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
    </el-card>

    <!-- 添加商品的对话框 -->
    <el-dialog title="添加商品" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addGoodsForm"
        ref="addGoodsFormRef"
        :rules="addGoodsFormRules"
        label-width="100px"
      >
        <el-row :gutter="50">
          <el-col :span="16">
            <el-form-item label="商品名" prop="name">
              <el-input v-model="addGoodsForm.name"></el-input>
            </el-form-item>
            <el-form-item label="价格(元)" prop="price">
              <el-input-number v-model="addGoodsForm.price" :min="0" :controls="false" :precision="2"></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="addGoodsForm.number" oninput ="value=value.replace(/[^0-9]/g,'')"></el-input>
            </el-form-item>
            <el-form-item label="重量(克)" prop="weight">
              <el-input v-model.number="addGoodsForm.weight" oninput ="value=value.replace(/[^0-9]/g,'')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-image :src="url" style="width:230px;height:230px;" alt="img"/>
          </el-col>
        </el-row>
        <el-form-item label="详细介绍" prop="introduce">
          <el-input v-model="addGoodsForm.introduce" type="textarea" :rows="3"></el-input>
        </el-form-item>
        <el-form-item label="图片url" prop="url">
          <el-input v-model="addGoodsForm.url" @blur="handleImgURL"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addGoods">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 商品详情 -->
    <el-dialog title="商品详情" :visible.sync="goodsDetailDialogVisible" width="50%" @close="goodsDetailDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="goodsDetailForm"
        ref="goodsDetailFormRef"
        label-width="100px"
      >
        <el-row :gutter="50">
          <el-col :span="16">
            <el-form-item label="商品名" prop="name">
              <el-input v-model="goodsDetailForm.name" readonly></el-input>
            </el-form-item>
            <el-form-item label="价格(元)" prop="price">
              <el-input-number v-model="goodsDetailForm.price" :min="0" :controls="false" :precision="2" readonly></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="goodsDetailForm.number" oninput="value=value.replace(/[^0-9]/g,'')" readonly></el-input>
            </el-form-item>
            <el-form-item label="重量(克)" prop="weight">
              <el-input v-model.number="goodsDetailForm.weight" oninput="value=value.replace(/[^0-9]/g,'')" readonly></el-input>
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
    <!-- 修改商品的对话框 -->
    <el-dialog title="编辑商品" :visible.sync="updateDialogVisible" width="50%" @close="updateDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addGoodsForm"
        ref="updateGoodsFormRef"
        :rules="addGoodsFormRules"
        label-width="100px"
      >
        <el-row :gutter="50">
          <el-col :span="16">
            <el-form-item label="商品名" prop="name">
              <el-input v-model="addGoodsForm.name"></el-input>
            </el-form-item>
            <el-form-item label="价格(元)" prop="price">
              <el-input-number v-model="addGoodsForm.price" :min="0" :controls="false" :precision="2"></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="addGoodsForm.number" oninput ="value=value.replace(/[^0-9]/g,'')"></el-input>
            </el-form-item>
            <el-form-item label="重量(克)" prop="weight">
              <el-input v-model.number="addGoodsForm.weight" oninput ="value=value.replace(/[^0-9]/g,'')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-image :src="url" style="width:230px;height:230px;" alt="img"/>
          </el-col>
        </el-row>
        <el-form-item label="详细介绍" prop="introduce">
          <el-input v-model="addGoodsForm.introduce" type="textarea" :rows="3"></el-input>
        </el-form-item>
        <el-form-item label="图片url" prop="url">
          <el-input v-model="addGoodsForm.url" @blur="handleImgURL"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateGoods">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      queryInfo: {
        name: '',
        pagenum: 1,
        pagesize: 5
      },
      // 商品列表
      goodsList: [],
      // 商品总数
      total: 0,
      // 添加商品对话框
      addDialogVisible: false,
      // 添加表单
      addGoodsForm: {
        name: '',
        price: '',
        number: '',
        weight: '',
        introduce: '',
        url: '',
      },
      // 表单验证规则
      addGoodsFormRules: {
        name: [
          {required: true, message: '请输入商品名', trigger: "blur"},
          {
            min: 1,
            max: 128,
            message: '名字长度在1～128个字',
            trigger: 'blur'
          }
        ],
        price: [
          {required: true, message: '请输入商品单价', trigger: 'blur'},
        ],
        number: [
          {required: true, message: '请输入商品数目', trigger: 'blur'},
        ],
        weight: [
          {required: true, message: '请输入重量，单位：克', trigger: 'blur'},
        ]
      },
      url : '',
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
      // 修改商品对话框
      updateDialogVisible: false,
    }
  },
  created() {
    this.getSellingGoodsList()
  },
  methods: {
    // 根据分页获取对应的商品列表
    async getSellingGoodsList() {
      const {data: res} = await this.$http.get('goods/getGoods', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取商品列表失败！ 原因: ' + res.msg)
      }
      this.goodsList = res.data.goods
      //   console.log(this.goodsList)
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
    // 监听 添加用户对话框的关闭事件
    addDialogClosed() {
      this.$refs.addGoodsFormRef.resetFields()
    },
    handleImgURL() {
        this.url = this.addGoodsForm.url
    },
    // 添加商品
    addGoods() {
      // 提交请求前，表单预验证
      this.$refs.addGoodsFormRef.validate(async valid => {
        // console.log(valid)
        // 表单预校验失败
        if (!valid) return
        const {data: res} = await this.$http.get('goods/addGoods', this.addGoodsForm)
        if (res.code !== 200) {
          this.$message.error('添加失败！')
          return
        }
        this.$message.success('添加商品成功！')
        this.addDialogVisible = false
        await this.getSellingGoodsList()
      })
    },
    goodsDetailDialogClosed() {
      this.goodsDetailDialogVisible = false
      this.$refs.goodsDetailFormRef.resetFields()
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
    async updateGoodsInfo(id) {
      console.log(id)
      let self = this
      this.goodsList.forEach(function (item) {
        if(item.id === id) {
          self.addGoodsForm = item
          console.log(item)
          return
        }
      })
    },
    updateDialogClosed() {
      this.$refs.goodsDetailFormRef.resetFields()
      this.$refs.addGoodsFormRef.resetFields()
    },
    updateGoods() {
      // todo
      console.log(this.addGoodsForm)
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

</style>

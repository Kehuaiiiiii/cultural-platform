<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="queryInfo.name" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="queryInfo.pagenum=1;getGoodsList()"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table class="table" :data="goodsList" border stripe highlight-current-row>
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="商品名称" prop="name" :resizable="false"></el-table-column>
        <el-table-column label="商品价格(元)" prop="price" width="140px" sortable :resizable="false"></el-table-column>
        <el-table-column label="商品重量(kg)" prop="weight" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="数量" prop="number" width="70px" :resizable="false"></el-table-column>
        <el-table-column label="上架时间" prop="created_time" width="100px" :resizable="false">
          <template slot-scope="scope">{{scope.row.created_time | dateFormat }}</template>
        </el-table-column>
        <el-table-column label="操作" width="100px" :resizable="false">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="查看详情" placement="top">
              <el-button type="primary" icon="el-icon-info" size="small" @click="getGoodsInfo(scope.row.id)" circle></el-button>
            </el-tooltip>
              <el-tooltip class="tooltip" effect="dark" content="购买" placement="top">
            <el-button type="success" icon="el-icon-shopping-bag-1" size="small" @click="buyGoods(scope.row.id)" circle></el-button>
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
              <el-input-number v-model="goodsDetailForm.price" :min="0" :controls="false" :precision="2" readonly></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="goodsDetailForm.number" oninput ="value=value.replace(/[^0-9]/g,'')" readonly></el-input>
            </el-form-item>
            <el-form-item label="重量(克)" prop="weight">
              <el-input v-model.number="goodsDetailForm.weight" oninput ="value=value.replace(/[^0-9]/g,'')" readonly></el-input>
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
    <!-- 购买商品 -->
    <el-dialog title="购买商品" :visible.sync="buyGoodsDialogVisible" width="50%" @close="buyGoodsDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="buyGoodsForm"
        ref="buyGoodsForm"
        :rules="buyGoodsFormRules"
        label-width="100px"
      >
        <el-row :gutter="50">
          <el-col :span="16">
            <el-form-item label="商品名" prop="name">
              <el-input v-model="buyGoodsForm.name" readonly></el-input>
            </el-form-item>
            <el-form-item label="价格(元)" prop="price">
              <el-input-number v-model="buyGoodsForm.price" :min="0" :controls="false" :precision="2" readonly></el-input-number>
            </el-form-item>
            <el-form-item label="数量" prop="number">
              <el-input v-model.number="buyGoodsForm.number" oninput ="value=value.replace(/[^0-9]/g,'')" @input="calcTotal"></el-input>
            </el-form-item>
            <el-form-item label="支付总计" prop="totol">
              <el-input-number v-model="buyGoodsForm.totol" :min="0" :controls="false" :precision="2" disabled></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-image :src="buyGoodsForm.url" style="width:230px;height:230px;" alt="img"/>
          </el-col>
        </el-row>
        <el-form-item label="收货地址" prop="weight">
          <el-input v-model="buyGoodsForm.addr"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="buyGoodsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="createOrder(buyGoodsForm)">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Vue from "vue";

export default {
  data () {
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
      // 购买商品对话框
      buyGoodsDialogVisible: false,
      // 购买商品表单
      buyGoodsForm: {
        name: '',
        price: 0,
        number: 1,
        addr: '',
        url: '',
        totol: '',
      },
      // 表单验证规则
      buyGoodsFormRules: {
        number: [
          {required: true, message: '请输入数量', trigger: "blur"},
          {
            type: 'number',
            min: 1,
            max: 99,
            message: '数目超过限制',
            trigger: 'blur'
          }
        ],
      },
    }
  },
  created () {
    this.getGoodsList()
    console.log(this.$route)
  },
  methods: {
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
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getGoodsList()
    },
    handleCurrentChange (newSize) {
      this.queryInfo.pagenum = newSize
      this.getGoodsList()
    },
    goodsDetailDialogClosed() {
      this.goodsDetailDialogVisible = false
      this.$refs.goodsDetailForm.resetFields()
    },
    getGoodsInfo(id) {
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
    calcTotal() {
      this.buyGoodsForm.totol = this.buyGoodsForm.number * this.buyGoodsForm.price
    },
    buyGoodsDialogClosed() {
      this.goodsDetailDialogVisible = false
      this.$refs.buyGoodsForm.resetFields()
    },
    async buyGoods(id) {
      this.buyGoodsDialogVisible = true
      console.log(id)
    },
    async createOrder(buyForm) {
      this.$refs.buyGoodsFormRef.validate(async valid => {
        if (!valid) return
        //todo 创建订单
        const {data: res} = await this.$http.get('order/add', this.addGoodsForm)
        if (res.code !== 200) {
          this.$message.error('购买失败！')
          return
        }
        this.$message.success('下单成功！')
        this.buyGoodsDialogVisible = false
        await this.getSellingGoodsList()
      })
    },
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

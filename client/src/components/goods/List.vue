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
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table class="table" :data="goodsList" border stripe highlight-current-row>
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="商品名称" prop="goods_name" :resizable="false"></el-table-column>
        <el-table-column label="商品价格(元)" prop="goods_price" width="140px" sortable :resizable="false"></el-table-column>
        <el-table-column label="商品重量(kg)" prop="goods_weight" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="数量" prop="goods_number" width="70px" :resizable="false"></el-table-column>
        <el-table-column label="上架时间" prop="add_time" width="100px" :resizable="false">
          <template slot-scope="scope">{{scope.row.add_time | dataFormat }}</template>
        </el-table-column>
        <el-table-column label="操作" width="100px" :resizable="false">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="查看详情" placement="top">
              <el-button type="primary" icon="el-icon-info" size="small" circle></el-button>
            </el-tooltip>
              <el-tooltip class="tooltip" effect="dark" content="购买" placement="top">
            <el-button type="success" icon="el-icon-shopping-bag-1" size="small" @click="" circle></el-button>
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
  </div>
</template>

<script>
export default {
  data () {
    return {
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 10
      },
      // 商品列表
      goodsList: [{
        goods_name: 'abc',
        goods_price: 200,
        goods_weight: 10,
        goods_number: 20,
        add_time: '2020-12-24'
      },{
        goods_name: 'abc',
        goods_price: 100,
        goods_weight: 5,
        goods_number: 1,
        add_time: '2020-11-24'
      }],
      // 商品总数
      total: 0
    }
  },
  created () {
    this.getGoodsList()
  },
  methods: {
    // 根据分页获取对应的商品列表
    async getGoodsList () {
      const { data: res } = await this.$http.get('goods', {
        params: this.queryInfo
      })
      if (res.data.status !== 200) {
        return this.$message.error('获取商品列表失败！')
      }
      this.goodsList = res.data.goods
      //   console.log(this.goodsList)
      this.total = res.data.total
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getGoodsList()
    },
    handleCurrentChange (newSize) {
      this.queryInfo.pagenum = newSize
      this.getGoodsList()
    }
  }
}
</script>

<style lang="less" scoped>

</style>

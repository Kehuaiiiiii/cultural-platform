<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>订单管理</el-breadcrumb-item>
      <el-breadcrumb-item>我的订单</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
<!--      <el-row :gutter="20">-->
<!--        <el-col :span="6">-->
<!--          <el-input placeholder="请输入内容" v-model="queryInfo.name" clearable @clear="getOrderList">-->
<!--            <el-button slot="append" icon="el-icon-search" @click="queryInfo.pagenum=1;getOrderList()"></el-button>-->
<!--          </el-input>-->
<!--        </el-col>-->
<!--      </el-row>-->
      <!-- 表格数据 -->
      <el-table class="table" :data="orderList" border stripe highlight-current-row>
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="商品名称" prop="goods_name" :resizable="false"></el-table-column>
        <el-table-column label="商品单价(元)" prop="goods_price" width="140px" sortable :resizable="false"></el-table-column>
        <el-table-column label="购买数量" prop="goods_number" width="70px" :resizable="false"></el-table-column>
        <el-table-column label="总计金额(元)" prop="order_price" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="支付状态" prop="pay_status" :formatter="payStatusFormat" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="物流状态" prop="send_status" :formatter="sendStatusFormat" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="下单时间" prop="created_time" width="100px" :resizable="false">
          <template slot-scope="scope">{{scope.row.created_time | dateFormat }}</template>
        </el-table-column>
        <el-table-column label="买家名称" prop="buyer_name" width="140px" :resizable="false"></el-table-column>
        <el-table-column label="卖家名称" prop="seller_name" width="140px" :resizable="false"></el-table-column>

        <el-table-column label="操作" width="100px" :resizable="false">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="支付订单" placement="top">
              <el-button type="primary" icon="el-icon-money" size="small" @click="" circle></el-button>
            </el-tooltip>
            <el-tooltip class="tooltip" effect="dark" content="确认收货" placement="top">
              <el-button type="success" icon="el-icon-check" size="small" @click="" circle></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
        <template slot="empty">
          <div class="noOrder">暂无数据</div>
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
import Vue from "vue";

export default {
  data () {
    return {
      queryInfo: {
        pagenum: 1,
        pagesize: 5
      },
      // 订单列表
      orderList: [],
      // 订单总数
      total: 0,
      payStatusMap: {
        0: '未支付',
        1: '已支付',
      },
      sendStatusMap: {
        0: '未发货',
        1: '已发货',
        2: '已收货',
      },

    }
  },
  created () {
    this.getOrderList()
  },
  methods: {
    // 根据分页获取对应的商品列表
    async getOrderList () {
      const { data: res } = await this.$http.get('orders/getOrder', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取订单列表失败！ 原因: ' + res.msg)
      }
      this.orderList = res.data.orderList
      this.total = res.data.total
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getOrderList()
    },
    handleCurrentChange (newSize) {
      this.queryInfo.pagenum = newSize
      this.getOrderList()
    },
    payStatusFormat(row, column, cellValue, index) {
      return this.payStatusMap[cellValue]
    },
    sendStatusFormat(row, column, cellValue, index) {
      return this.sendStatusMap[cellValue]
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

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
      <el-row :gutter="20">
        <el-col :span="3">
          <el-select v-model="queryInfo.payStatus" placeholder="支付状态" clearable>
            <el-option
              v-for="item in queryPayStatusParams"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="queryInfo.sendStatus" placeholder="物流状态" clearable>
            <el-option
              v-for="item in querySendStatusParams"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="2">
          <el-button icon="el-icon-search" @click="queryInfo.pagenum=1;getOrderList()"></el-button>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table class="table" :data="orderList" border highlight-current-row :row-class-name="tableRowClassName">
        <el-table-column type="index" :resizable="false"></el-table-column>
        <el-table-column label="商品名称" prop="goods_name" :resizable="false"></el-table-column>
        <el-table-column label="商品单价(元)" prop="goods_price" width="140px" sortable :resizable="false"></el-table-column>
        <el-table-column label="购买数量" prop="goods_number" width="120px" :resizable="false" sortable></el-table-column>
        <el-table-column label="总计金额(元)" prop="order_price" width="140px" :resizable="false" sortable></el-table-column>
        <el-table-column label="支付状态" prop="pay_status" :formatter="payStatusFormat" width="120px" :resizable="false"
                         sortable></el-table-column>
        <el-table-column label="物流状态" prop="send_status" :formatter="sendStatusFormat" width="120px" :resizable="false"
                         sortable></el-table-column>
        <el-table-column label="下单时间" prop="created_time" width="140px" :resizable="false" sortable>
          <template slot-scope="scope">{{ scope.row.created_time | dateFormat }}</template>
        </el-table-column>
        <!--        <el-table-column label="买家名称" prop="buyer_name" width="140px" :resizable="false"></el-table-column>-->
        <el-table-column label="卖家名称" prop="seller_name" width="140px" :resizable="false"></el-table-column>

        <el-table-column label="操作" width="100px" :resizable="false">
          <template slot-scope="scope">
            <el-tooltip class="tooltip" effect="dark" content="支付订单" placement="top">
              <el-button type="primary" icon="el-icon-money" size="small" @click="payOrder(scope.row.id)"
                         circle></el-button>
            </el-tooltip>
            <el-tooltip class="tooltip" effect="dark" content="确认收货" placement="top">
              <el-button type="success" icon="el-icon-check" size="small" @click="receiveOrder(scope.row.id)"
                         circle></el-button>
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

export default {
  data() {
    return {
      queryInfo: {
        pagenum: 1,
        pagesize: 5,
        payStatus: null,
        sendStatus: null,
      },
      queryPayStatusParams: [{
        value: 0,
        label: '未支付'
      }, {
        value: 1,
        label: '已支付'
      }],
      querySendStatusParams: [{
        value: 0,
        label: '未发货'
      }, {
        value: 1,
        label: '已发货'
      }, {
        value: 2,
        label: '已收货'
      }],
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
  created() {
    this.getOrderList()
  },
  methods: {
    // 根据分页获取对应的商品列表
    async getOrderList() {
      console.log(this.queryInfo)
      if(this.queryInfo.payStatus === '')
        this.queryInfo.payStatus = null
      if(this.queryInfo.sendStatus === '')
        this.queryInfo.sendStatus = null

      const {data: res} = await this.$http.get('orders/getOrder', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取订单列表失败！ 原因: ' + res.msg)
      }
      this.orderList = res.data.orderList
      this.total = res.data.total
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
      this.getOrderList()
    },
    handleCurrentChange(newSize) {
      this.queryInfo.pagenum = newSize
      this.getOrderList()
    },
    payStatusFormat(row, column, cellValue, index) {
      return this.payStatusMap[cellValue]
    },
    sendStatusFormat(row, column, cellValue, index) {
      return this.sendStatusMap[cellValue]
    },
    tableRowClassName({row, rowIndex}) {
      if (row.pay_status === 1 && row.send_status === 2) {
        return 'success-row';
      } else if (row.pay_status === 0) {
        return 'warning-row';
      }
      return '';
    },
    payOrder(id) {
      console.log(id)
      const vx = require("../../assets/vx_pay.png");
      const zfb = require("../../assets/zfb_pay.png");
      this.$confirm("<img src=" + vx + " width=190px height=200px style='margin-right:10px'/>" +
        "<img src=" + zfb + " width=190px height=200px/>", '支付', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '我已支付完成',
        cancelButtonText: '取消',
      }).then(async () => {
        let order = null
        this.orderList.forEach(function (item) {
          if (item.id === id) {
            order = item
            return
          }
        })
        if (order == null) {
          this.$message({
            type: 'error',
            message: '系统错误'
          });
          return
        }
        if (order.pay_status === 1) {
          this.$message({
            type: 'warning',
            message: '您已完成支付，无需重复提交'
          });
          return
        }
        order.pay_status = 1
        const {data: res} = await this.$http.get('orders/updOrderPay', {
          params: order
        })
        if (res.code !== 200) {
          return this.$message.error('支付失败！ 原因: ' + res.msg)
        }
        this.$message({
          type: 'success',
          message: '支付成功!'
        });
        await this.getOrderList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消支付'
        });
      });
    },
    receiveOrder(id) {
      console.log(id)
      this.$confirm('确定收货？', '收货确认', {
        confirmButtonText: '完成收货',
        cancelButtonText: '取消',
      }).then(async () => {
        let order = null
        this.orderList.forEach(function (item) {
          if (item.id === id) {
            order = item
            return
          }
        })
        if (order == null) {
          this.$message({
            type: 'error',
            message: '系统错误'
          });
          return
        }
        if (order.send_status === 0) {
          this.$message({
            type: 'warning',
            message: '卖家尚未发货，无法收货'
          });
          return
        }
        if (order.send_status === 2) {
          this.$message({
            type: 'warning',
            message: '您已完成交易，无需重复操作'
          });
          return
        }
        order.send_status = 2
        const {data: res} = await this.$http.get('orders/updOrderSend', {
          params: order
        })
        if (res.code !== 200) {
          return this.$message.error('更新状态失败！ 原因: ' + res.msg)
        }
        this.$message({
          type: 'success',
          message: '收货完成!'
        });
        await this.getOrderList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消操作'
        });
      });
    },
  },
  filters: {
    dateFormat: function (originVal) {
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

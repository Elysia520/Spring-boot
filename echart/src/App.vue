<template>
<div id="app">
  <h3>图形展示</h3>
  <div ref="chart" style="width: 600px; height: 400px;"></div>
  <div ref="pieChart" style="width: 600px; height: 400px;"></div>
</div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';


export default {
  name: 'EchartDemo',
  mounted() {
    this.fetchDataAndDrawChart();//柱状图绘制方法
    this.fetchDataAndDrawPieChart(); // 饼状图绘制方法
  },
  methods: {
    fetchDataAndDrawChart() {
      axios.get('http://localhost:8081/workstep/top-used-tools', {
        params: {
          pageNum: 1, // 或其他您希望请求的页码
          pageSize: 10 // 或其他您希望的页面大小
        }
      })
          .then(response => {
            const { data } = response;
            this.drawChart(data);
          })
          .catch(error => console.error('Error fetching data:', error));
    },
    drawChart(data) {
      const myChart = echarts.init(this.$refs.chart);
      //柱状图配置
      const option = {
        title: {
          text: '工具使用频率前十柱状图'
        },
        tooltip: {trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          data: data.map(item => item.toolName)
        },
        yAxis: {},
        series: [{
          name: '使用次数',
          type: 'bar',

          data: data.map(item => item.usageCount)
        }]
      };
      myChart.setOption(option);
    },

    fetchDataAndDrawPieChart() {
      axios.get('http://localhost:8081/workstep/1/worksteps/frequencies') // 使用真实端口和partId
          .then(response => {
            const data = response.data;
            this.drawPieChart(data);
          })
          .catch(error => console.error('Error fetching pie chart data:', error));
    },
    drawPieChart(data) {
      const pieChart = echarts.init(this.$refs.pieChart); // 创建新的DOM元素进行绘图

      // 格式化数据以适配饼图的数据格式
      const formattedData = data.map(item => ({
        value: item.frequency,
        name: item.workstepName
      }));

      const option = {
        title: {
          text: '工步分析玫瑰图',
          subtext: '200md-c主轴箱',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          top: 'bottom',
          data: formattedData.map(item => item.name)
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: 'Workstep Frequency',
            type: 'pie',
            radius: [20, 140],
            center: ['50%', '50%'], // 根据实际需求调整位置
            roseType: 'radius', // 可以是'radius'（半径模式）或'area'（面积模式）
            itemStyle: {
              borderRadius: 5
            },
            data: formattedData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      pieChart.setOption(option);
    }



  },



};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

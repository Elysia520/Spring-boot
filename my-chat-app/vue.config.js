const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    proxy: {
      '/chat': {
        target: 'http://localhost:8081', // 你的Spring Boot应用的地址
        changeOrigin: true
      }
    }
  }
};

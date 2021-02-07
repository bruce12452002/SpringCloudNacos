官網文件：
https://github.com/alibaba/Sentinel/wiki/%E7%BD%91%E5%85%B3%E9%99%90%E6%B5%81
https://sentinelguard.io/zh-cn/docs/general-configuration.html

sentinel 啟動命令：
java -Dserver.port=9090 -jar sentinel-dashboard-1.8.0.jar

gateway VM 參數：
-Dcsp.sentinel.dashboard.server=localhost:9090 -Dcsp.sentinel.app.type=1


sentinel 畫面的「API 管理」的設定
精確：/xxx/port
前綴：/xxx/*
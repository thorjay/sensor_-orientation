# sensor
Android设备传感器Sensor使用样例，真实场景：指南针，水平仪等等；

## 流程

```flow
 	sensorManager=>start: 获取服务
 	sensor=>operation: 获取传感器
  sensorEventListener=>operation: 传感器事件
  register=>operation: 配置&注册监听
  unregister=>end: 注销
  
  sensorManager->sensor->sensorEventListener->register->unregister
```

* 获取传感器 & 注册监听 & 注销

  > ```
  > DegreeSensor
  > ```

* 监听事件

  > ```
  > DegreeSensorEventListner
  > ```

* 角度计算

  > ```
  > DegreeDataCal
  > ```

## 效果

![demo1](demo/demo1.png)

## TODO

* [ ] 界面展示优化

## 详细介绍

[传感器_方位水平测量](https://thorjay.github.io/2022/09/21/%E4%BC%A0%E6%84%9F%E5%99%A8-%E6%96%B9%E4%BD%8D%E6%B0%B4%E5%B9%B3%E6%B5%8B%E9%87%8F/)

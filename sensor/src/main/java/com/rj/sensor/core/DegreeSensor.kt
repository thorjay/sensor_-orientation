package com.rj.sensor.core

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * @decrpition 获取传感器列表
 * @time 2022/8/25 16:11
 * @param context
 * @return
 */
fun getSensorList(context : Context) : List<Sensor>{
    val sensorMgr = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    return sensorMgr.getSensorList(Sensor.TYPE_ALL)
}

/**
 * 判断设备中指定的传感器是否存在
 * @author:leijie
 * @date:2022/8/25 16:11
 * @param type 指定sensor类型
 * @see Sensor
 */
fun isSensorExist(context: Context,type : Int) : Boolean{
    val sensorMgr = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    return sensorMgr.getDefaultSensor(type) != null
}
/**
 * 方位和下倾角度传感器
 *
 * @author:leijie
 * @date:2022/8/25 15:49
 */
class DegreeSensor constructor(var context: Context,var sensorEventListener: SensorEventListener) {
    private lateinit var sensorMgr : SensorManager

    init {
        sensorMgr = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    /**
     * @decrpition 注册传感器监听
     * 位置传感器(地磁场强度)
     * 运动传感器(加速力包括重力）
     * @time 2022/8/30 09:41
     * @param
     * @return
     */
    fun start(){
        sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
                accSensor -> sensorMgr.registerListener(sensorEventListener,accSensor,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_UI)
        }
        sensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also {
                magSensor -> sensorMgr.registerListener(sensorEventListener,magSensor,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_UI)
        }
    }

    /**
     * @decrpition 取消传感器监听
     * @time 2022/8/30 09:51
     * @param
     * @return
     */
    fun stop(){
        sensorMgr.unregisterListener(sensorEventListener)
    }



}
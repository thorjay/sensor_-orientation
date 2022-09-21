package com.rj.sensor.core

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.rj.sensor.model.AngelData
import com.rj.sensor.view.ui.main.MainViewModel
import timber.log.Timber

/**
 * 方位和下倾角度监听器
 * @author:leijie
 * @date:2022/8/30 10:00
 */
class DegreeSensorEventListner(val viewModel: MainViewModel) : SensorEventListener{

    //加速度(带重力)事件数据
    private val  accelerometerReading = FloatArray(3)
    //磁感器事件事件数据
    private val magnetometerReading = FloatArray(3)

    override fun onSensorChanged(event: SensorEvent?) {
        // 处理数据
        event?.sensor?.type.let {
            when(it){
                //加速度(带重力)
                Sensor.TYPE_ACCELEROMETER -> System.arraycopy(event?.values,0,accelerometerReading,0,accelerometerReading.size)
                //磁力
                Sensor.TYPE_MAGNETIC_FIELD -> System.arraycopy(event?.values,0,magnetometerReading,0,magnetometerReading.size)
            }
            val cal = DegreeDataCal(accelerometerReading,magnetometerReading)
            cal.cal().let {
                result -> viewModel.updateAngel(result = AngelData(result[0], result[1], result[2]))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Timber.i("sensor的信息 : name:%s;version:%s;type:%s",sensor?.name,sensor?.version,sensor?.type)
        Timber.i("accuracy : $accuracy")
    }

}
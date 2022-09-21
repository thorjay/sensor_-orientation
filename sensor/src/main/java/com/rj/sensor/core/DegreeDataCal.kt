package com.rj.sensor.core

import android.hardware.SensorManager
import com.rj.sensor.enum.AzimuthEnum
import timber.log.Timber

/**
 * 设备屏幕方向的计算处理
 * azimuth方位角(z轴角度 0~360)，俯仰角pitch(x轴旋转 -180~180)，倾侧角heel(rool)(饶y轴旋转 -90~90)；
 * @author:leijie
 * @date:2022/8/30 14:44
 */
class DegreeDataCal(val accArray: FloatArray,val magArray: FloatArray) {

    /**
     * @decrpition 计算
     * @time 2022/8/30 14:50
     * @param
     * @return 方位角(z轴角度)，俯仰角(x轴旋转)，倾侧角(绕y轴旋转) FloatArray数组
     */
    fun cal() : FloatArray{
        // Rotation matrix based on current readings from accelerometer and magnetometer.
        val rotationMatrix = FloatArray(9)
        SensorManager.getRotationMatrix(rotationMatrix,null,accArray,magArray)
        // Express the updated rotation matrix as three orientation angles.
        val orientationAngles = FloatArray(3)
        rotationMatrix.forEach {
            it.let { Timber.i(it.toString()) }
        }
        SensorManager.getOrientation(rotationMatrix,orientationAngles)
        return orientationAngles
    }

    companion object{
        /**
         * @decrpition 方位角度
         * 范围为[-π~π]-180~180，基准：手机上边缘对正北为0；手机前端指向往右(东)偏转，角度最大可达180，当到正南时，为180；往左(西)，角度为负，最小可达-180
         * @time 2022/8/31 17:47
         * @param
         * @return
         */
        fun angelToAzimuth(radian : Float) : Float{
            var angle = radian.let { (radian / Math.PI) * 180 }.toFloat()
            if (angle < 0 && angle >= -180){
                angle += 360
            }
            return angle
        }

        fun getAzimuthEnum(angel : Float) : AzimuthEnum? {
                if(angel == 0f){
                    return AzimuthEnum.NORTH
                }else if(angel in 0f .. 90f){
                    return AzimuthEnum.NOR_EA
                }else if(angel == 90f){
                    return AzimuthEnum.EAST
                }else if(angel in 90f .. 180f){
                    return AzimuthEnum.SOU_EA
                }else if(angel == 180f){
                    return AzimuthEnum.SOUTH
                }else if(angel in 180f .. 270f){
                    return AzimuthEnum.SOU_WE
                }else if(angel == 270f){
                    return AzimuthEnum.WEST
                }else if(angel in 270f .. 360f){
                    return AzimuthEnum.NOR_WE
                }
            return null
            }
        }
    }
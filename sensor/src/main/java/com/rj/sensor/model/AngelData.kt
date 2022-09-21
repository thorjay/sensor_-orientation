package com.rj.sensor.model

/**
 * 屏幕方向角度
 * azimuth 方位角(z轴角度 0~360)，pitch 俯仰角(x轴旋转 -180~180)，heel 倾侧角(饶y轴旋转 -90~90)；
 * @author:leijie
 * @date:2022/8/30 16:57
 */
data class AngelData(val azimuth : Float,val pitch : Float,val heel : Float)

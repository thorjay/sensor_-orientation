package com.rj.sensor.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rj.sensor.model.AngelData

/**
 * 对Angel数据的处理
 * @author:leijie
 * @date:2022/8/31 09:25
 */
class MainViewModel : ViewModel() {

    //展示的角度数据，使用MutableLiveData存储
    private val angle = MutableLiveData<AngelData>()

    /**
     * @decrpition 获取角度数据
     * @time 2022/8/31 15:02
     * @param null
     * @return
     */
    fun getAngle() : LiveData<AngelData>{
        return angle
    }

    /**
     * @decrpition 更新数据
     * @time 2022/8/31 15:02
     * @param null
     * @return
     */
    fun updateAngel(result : AngelData){
        angle.value = result
    }
}


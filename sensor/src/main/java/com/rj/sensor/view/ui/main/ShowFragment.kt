package com.rj.sensor.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rj.sensor.core.DegreeDataCal
import com.rj.sensor.core.DegreeSensorEventListner
import com.rj.sensor.core.DegreeSensor
import com.rj.sensor.databinding.MainFragmentBinding

class ShowFragment : Fragment() {

    companion object {
        fun newInstance() = ShowFragment()
    }
    private lateinit var binding : MainFragmentBinding

    private lateinit var viewModel: MainViewModel
    //使用
//    private val viewModel1 : MainViewModel by activityViewModels()

    private lateinit var sensorListner : DegreeSensorEventListner

    private lateinit var degreeSensor : DegreeSensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sensorListner = DegreeSensorEventListner(viewModel)
        degreeSensor = DegreeSensor(requireContext(),sensorListner)
        // TODO: Use the ViewModel
        viewModel.getAngle().observe(this.viewLifecycleOwner) {
            binding.angleAzimuth.text =
                "<方位:屏幕上边缘与磁北向之间的角度> \n" + "幅度:${it.azimuth.toString()} \n 角度: ${Math.toDegrees(it.azimuth.toDouble())} \n" + "转换: ${DegreeDataCal.angelToAzimuth(
                    it.azimuth.toFloat() 
                )} ${DegreeDataCal.getAzimuthEnum(DegreeDataCal.angelToAzimuth(it.azimuth.toFloat()))?.azimuth}"
            binding.anglePitch.text =
                "<水平:屏幕与垂直于地面的平面之间的角度> \n" + "幅度：${it.pitch.toString()} \n 角度: ${Math.toDegrees(it.pitch.toDouble())}"
            binding.angleHeel.text =
                "<俯仰:屏幕与平行于地面的平面的角度> \n" + "幅度：${it.heel.toString()}  \n 角度: ${Math.toDegrees(it.heel.toDouble())}"
        }
        binding.btnStart.setOnClickListener {
            degreeSensor.start()
        }

        binding.btnStop.setOnClickListener {
            degreeSensor.stop()
        }

    }

}
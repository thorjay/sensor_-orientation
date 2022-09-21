package com.rj.sensor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rj.sensor.R
import com.rj.sensor.view.ui.main.ShowFragment

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowFragment.newInstance())
                .commitNow()
        }
    }
}
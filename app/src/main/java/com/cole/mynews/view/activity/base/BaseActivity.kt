package com.cole.mynews.view.activity.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics

abstract class BaseActivity : AppCompatActivity() {

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        getScreenPixel()
        initView()
    }

    private fun getScreenPixel() {
        val metrics = DisplayMetrics()
        val manager = windowManager
        manager.defaultDisplay.getMetrics(metrics)
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

}

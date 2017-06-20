package com.cole.mynews.ui.activity.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity : AppCompatActivity() {

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (isRegisterEventBusHere()) {
            EventBus.getDefault().register(this)
        }
        getScreenPixel()
        initPresenter()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBusHere()) {
            EventBus.getDefault().unregister(this)
        }
    }

    private fun getScreenPixel() {
        val metrics = DisplayMetrics()
        val manager = windowManager
        manager.defaultDisplay.getMetrics(metrics)
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels
    }


    /**
     * 通信管道要求必须有的一个方法

     * @param event 通信管道的事件
     */
    @Subscribe
    fun onEvent(event: Any?) {
        if (event != null) {
            eventArriving(event)
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initPresenter()

    abstract fun getData(page:Int)

    /**
     * 该页面是否注册eventBus
     *
     * @return true:注册; false:不注册
     */
    abstract fun isRegisterEventBusHere(): Boolean

    /**
     * 处理eventBus的方法
     *
     * @param event 管道通信发送过来的方法
     */
    @Subscribe
    abstract fun eventArriving(event: Any)

}

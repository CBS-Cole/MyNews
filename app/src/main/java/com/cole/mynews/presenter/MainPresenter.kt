package com.cole.mynews.presenter

import android.app.Activity
import com.cole.mynews.views.IMainView

/**
 * Created by cole on 2017/6/19.
 * 描述：
 */
class MainPresenter() : BasePresenter() {
    var mMainView: IMainView? = null

    constructor(activity: Activity,mainView: IMainView?):this(){
        mMainView=mainView
        super<BasePresenter>.BasePresenter(activity)
    }
}
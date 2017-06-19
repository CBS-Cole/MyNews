package com.cole.mynews.presenter

import android.app.Activity
import rx.subscriptions.CompositeSubscription

/**
 * Created by cole on 2017/6/19.
 * 描述：
 */
open class BasePresenter {
    var mActivity: Activity? = null
    fun BasePresenter(activity: Activity) {
        mActivity = activity
    }

    var mSubscription: CompositeSubscription? = null

    init {
        mSubscription = CompositeSubscription()
    }

    open fun unSubscriber() {
        mSubscription?.unsubscribe()
    }
}
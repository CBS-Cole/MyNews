package com.cole.mynews.views

/**
 * Created by cole on 2017/6/19.
 * 描述：
 */
interface IBaseView {
    fun showProgress() {}

    fun hideProgress() {}

    fun showProgressDialog(msg: String? = null) {}

    fun hideProgressDialog() {}
}
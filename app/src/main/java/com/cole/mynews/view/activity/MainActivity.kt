package com.cole.mynews.view.activity

import android.support.v7.app.ActionBarDrawerToggle
import com.cole.mynews.R
import com.cole.mynews.view.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val params = mNavigation.layoutParams
        params.width=screenWidth - screenWidth / 4
        mNavigation.layoutParams = params
    }
}

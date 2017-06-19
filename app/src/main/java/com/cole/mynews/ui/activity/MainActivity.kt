package com.cole.mynews.ui.activity

import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.cole.mynews.R
import com.cole.mynews.presenter.MainPresenter
import com.cole.mynews.ui.activity.base.BaseActivity
import com.cole.mynews.views.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, IMainView {

    private var mMainPresenter: MainPresenter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val params = mNavigation.layoutParams
        params.width = screenWidth - screenWidth / 4
        mNavigation.layoutParams = params
    }

    override fun initPresenter() {
        mMainPresenter = MainPresenter(this,this)
    }

    override fun isRegisterEventBusHere(): Boolean {
        return false
    }

    override fun eventArriving(event: Any) {

    }

    override fun onStart() {
        super.onStart()
        bindEvent()
    }

    private fun bindEvent() {
        mNavigation.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

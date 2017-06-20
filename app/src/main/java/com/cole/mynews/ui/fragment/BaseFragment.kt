package com.cole.mynews.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cole.mynews.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by cole on 2017/6/20.
 * 描述：
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getContentViewLayoutID() != 0) {
            return inflater!!.inflate(getContentViewLayoutID(), null)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isRegisterEventBusHere()) {
            EventBus.getDefault().register(this)
        }
        initView()
        initRefreshLayout()
        initRecyclerView()
        initPresenter()
    }

    fun initRefreshLayout() {
        getRefreshLayout()?.setColorSchemeResources(R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent)
        getRefreshLayout()?.setOnRefreshListener { getData(0) }
    }

    fun initRecyclerView() {
        getRecyclerView()?.layoutManager = getLinearLayoutManager()
        getRecyclerView()?.itemAnimator = DefaultItemAnimator()
        getRecyclerView()?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val linearManager = recyclerView?.layoutManager as LinearLayoutManager
                val position = linearManager.findLastVisibleItemPosition()
                LoadMoreData(position)
//                if (mShots?.isNotEmpty()!! && position == mShots?.size!!) {
//                    if (!isLoading) {
//                        mPage += 1
//                        getShots(true)
//                    }
//                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isRegisterEventBusHere()) {
            EventBus.getDefault().unregister(this)
        }
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

    abstract fun getContentViewLayoutID(): Int

    abstract fun getRefreshLayout(): SwipeRefreshLayout?

    abstract fun getRecyclerView(): RecyclerView?

    abstract fun getLinearLayoutManager(): LinearLayoutManager?

    abstract fun initPresenter()

    abstract fun initView()

    abstract fun getData(page: Int)

    abstract fun LoadMoreData(position: Int)

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
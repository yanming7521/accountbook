package com.example.accountbook.service

import android.app.Activity
import com.blankj.utilcode.util.Utils
import com.example.accountbook.ui.flow.FlowView

/**
 * Created by HouWei on 2023/10/10.
 */
object BackgroundService : Utils.OnAppStatusChangedListener {

    private var isTouched = false

    val mFlowView: FlowView by lazy {
        FlowView(Utils.getApp()).apply {
            addView()
        }
    }


    /**
     * Touch lazy init views
     * 主动加载一些状态监听view
     */
    fun lateInit() {
        if (isTouched) {
            return
        }
        isTouched = true
        // 触发懒加载
        mFlowView
    }


    override fun onForeground(activity: Activity?) {
        // 进入前台
    }

    override fun onBackground(activity: Activity?) {
        // 进入后台
        mFlowView.openFlowView()
    }

}

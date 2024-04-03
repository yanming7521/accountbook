package com.example.accountbook.ui.flow

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.accountbook.R
import com.example.accountbook.common.flow_window.BaseWindow

/**
 * @author: YanMinng
 * @date: 2024/3/28
 * @description:
 */
class FlowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseWindow(context, attrs, defStyleAttr) {
    private val appName: TextView by lazy {
        findViewById(R.id.appName)
    }

    override fun attachLayoutParams() {
        makeLayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            0,
            0,
            Gravity.TOP or Gravity.END,
            false
        )
    }

    override fun attachLayoutRes(): Int {
        return R.layout.flow_view
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun openFlowView() {
        addView()
    }

    fun setAppName(appName: String) {
        this.appName.text = appName
    }
}
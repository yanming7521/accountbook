package com.example.accountbook.common.flow_window

import android.content.Context
import android.content.res.Resources
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import me.jessyan.autosize.AutoSizeCompat

/**
 * Created by HouWei on 2022/3/3.
 */
abstract class BaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun getResources(): Resources {
        try {
            if (Looper.getMainLooper().thread === Thread.currentThread()) {
                AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.getResources()
    }

    init {
        initView()
    }

    open fun onCreate() {}

    open fun onDestroy() {}

    private fun initView() {
        View.inflate(context, attachLayoutRes(), this)
    }

    abstract fun attachLayoutRes(): Int

}
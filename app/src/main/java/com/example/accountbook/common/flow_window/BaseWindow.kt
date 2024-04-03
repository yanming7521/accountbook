package com.example.accountbook.common.flow_window

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.PixelFormat
import android.os.Build
import android.util.AttributeSet
import android.view.WindowManager
import me.jessyan.autosize.AutoSizeCompat
import java.util.concurrent.Semaphore

/**
 * Created by HouWei on 2022/3/3.
 */
abstract class BaseWindow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseView(context, attrs, defStyleAttr) {
    private var windowManager: WindowManager =
        context.getSystemService(WINDOW_SERVICE) as WindowManager
    private var layoutParams: WindowManager.LayoutParams? = null
    private var mSemaphore = Semaphore(1)
    private var mHeight = 0
    protected abstract fun attachLayoutParams()

    fun addView(): Boolean {
        if (layoutParams == null) {
            attachLayoutParams()
        }
        try {
            mSemaphore.acquire()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        AutoSizeCompat.autoConvertDensityOfGlobal(resources)
        layoutParams?.let { layoutParams ->
            if (parent == null) {
                mHeight = layoutParams.height
                windowManager.addView(this, layoutParams)
                onCreate()
                mSemaphore.release()
                return true
            }
        }
        mSemaphore.release()
        return false
    }

    fun removeView(): Boolean {
        try {
            mSemaphore.acquire()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        parent?.let { parent ->
            windowManager.removeView(this)
            onDestroy()
            mSemaphore.release()
            return true
        }
        mSemaphore.release()
        return false
    }

    fun updateViewLayout(x: Int, y: Int) {
        layoutParams?.let {
            it.x = x
            it.y = y
            windowManager.updateViewLayout(this, it)
        }
    }

    @SuppressLint("InlinedApi")
    @SuppressWarnings("deprecation")
    protected fun makeLayoutParams(
        width: Int,
        height: Int,
        x: Int,
        y: Int,
        gravity: Int,
        isFocusable: Boolean
    ) {
        if (this.layoutParams == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (isFocusable) {
                    this.layoutParams = WindowManager.LayoutParams(
                        width,
                        height,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                                WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                                or WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                        PixelFormat.TRANSPARENT
                    )
                } else {
                    this.layoutParams = WindowManager.LayoutParams(
                        width,
                        height,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                or WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                                or WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                        PixelFormat.TRANSPARENT
                    )
                }
                layoutParams?.gravity = gravity
                layoutParams?.x = x
                layoutParams?.y = y
            } else {
                if (isFocusable) {
                    this.layoutParams = WindowManager.LayoutParams(
                        width,
                        height,
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                or WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                                or WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                        PixelFormat.TRANSPARENT
                    )
                } else {
                    this.layoutParams = WindowManager.LayoutParams(
                        width,
                        height,
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                or WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                                or WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                        PixelFormat.TRANSPARENT
                    )
                }
                layoutParams?.gravity = gravity
                layoutParams?.x = x
                layoutParams?.y = y
            }
        }

    }

}
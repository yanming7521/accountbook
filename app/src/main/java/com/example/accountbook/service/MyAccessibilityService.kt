package com.example.accountbook.service


import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.drake.net.utils.scope
import com.example.accountbook.service.BackgroundService.mFlowView

/**
 * @author: YanMinng
 * @date: 2024/3/28
 * @description: 无障碍服务
 */
class MyAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            packageNames = AppUtils.getAppsInfo().map { it.packageName }.toTypedArray()
            LogUtils.a("MyAccessibilityService ${packageNames.joinToString(",")}")
            feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
            flags =
                AccessibilityServiceInfo.DEFAULT or AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS
        }
        serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event ?: return
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            || event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
        ) {
            val root = rootInActiveWindow ?: return
            val packageName = event.packageName.toString()
            var activityName: String? = null

            root.findAccessibilityNodeInfosByText("关闭").let {
                if (it.isNotEmpty()) {
                    activityName = it[0].className.toString()
                }
            }

            // 输出当前顶层Activity的包名和类名
            LogUtils.d("MyAccessibilityService", "Top Activity Package: $packageName")
            LogUtils.d("MyAccessibilityService", "Top Activity Name: $activityName")
            scope {
                mFlowView.setAppName(packageName)
            }
        }
    }

    override fun onInterrupt() {
        // 不在此处处理中断逻辑
    }
}

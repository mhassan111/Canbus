package com.screening.app.accessibility.events

import android.content.Context
import android.view.accessibility.AccessibilityNodeInfo
import com.screening.app.interfaces.AccessibilityEventListener
import com.screening.app.models.AccessibilityEventModel
import com.screening.app.utilities.logVerbose

class WindowStateChangeEventListener : AccessibilityEventListener {

    override fun onAccessibilityEvent(
        context: Context?,
        accessibilityEventModel: AccessibilityEventModel?
    ) {
        traverseThroughWindowStateChanged(context!!, accessibilityEventModel!!)
    }

    @Throws(Exception::class)
    private fun traverseThroughWindowStateChanged(
        context: Context,
        accessibilityEventModel: AccessibilityEventModel
    ) {

        val nodeInfo: AccessibilityNodeInfo? = accessibilityEventModel.eventSource
        val rootInWindow: AccessibilityNodeInfo? = accessibilityEventModel.rootInWindow
        val eventPackage: String = accessibilityEventModel.packageName
        val eventClassName: String = accessibilityEventModel.eventClassName
        val eventText: String = accessibilityEventModel.eventText

        executeWindowStateChangeTask(
            context,
            rootInWindow,
            eventPackage,
            true,
            rootInWindow?.childCount ?: 0,
            0
        )
    }

    private fun executeWindowStateChangeTask(
        context: Context,
        nodeInfo: AccessibilityNodeInfo?,
        pkgName: String,
        firstIteration: Boolean,
        totalChildCount: Int,
        currentChild: Int,
        iteration: Int = 0
    ) {
        try {
            if (firstIteration) {
                logVerbose("")
            }

            val sourceChildCount: Int = nodeInfo?.childCount ?: 0
            if (totalChildCount == currentChild) {
                if (sourceChildCount == 0) {
                    return
                }
            }

            for (i in 0 until sourceChildCount) {
                val childNodeInfo: AccessibilityNodeInfo? = nodeInfo?.getChild(i)
                if (childNodeInfo != null) {
                    executeWindowStateChangeTask(
                        context,
                        childNodeInfo,
                        pkgName,
                        false,
                        sourceChildCount,
                        i + 1,
                        iteration = iteration + 1
                    )
                    childNodeInfo.recycle()
                } else {
                    return
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
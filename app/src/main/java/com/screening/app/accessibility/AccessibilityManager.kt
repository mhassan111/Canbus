package com.screening.app.accessibility

import android.content.Context
import com.screening.app.accessibility.events.*
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.models.AccessibilityEventModel
import kotlinx.coroutines.CoroutineScope

class AccessibilityManager(private val context: Context) {

    fun manageEvent(
        coroutineScope: CoroutineScope,
        phoneNumberRepository: PhoneNumberRepository,
        eventType: AccessibilityEventType,
        accessibilityEventModel: AccessibilityEventModel,
    ) {
        when (eventType) {
            AccessibilityEventType.TYPE_VIEW_CLICKED -> {
                ViewLongClickedEventListener().onAccessibilityEvent(
                    context,
                    accessibilityEventModel
                )
            }
            AccessibilityEventType.TYPE_VIEW_LONG_CLICKED -> {
                ViewClickedEventListener().onAccessibilityEvent(context, accessibilityEventModel)
            }
            AccessibilityEventType.TYPE_WINDOW_STATE_CHANGED -> {
                WindowStateChangeEventListener().onAccessibilityEvent(
                    context,
                    accessibilityEventModel
                )
            }
            AccessibilityEventType.TYPE_WINDOW_CONTENT_CHANGED -> {
                WindowContentChangeEventListener().onAccessibilityEvent(
                    context,
                    accessibilityEventModel
                )
            }
            AccessibilityEventType.TYPE_VIEW_TEXT_CHANGED -> {
                ViewTextChangeEventListener().onAccessibilityEvent(context, accessibilityEventModel)
            }
            AccessibilityEventType.TYPE_NOTIFICATION_STATE_CHANGED -> {
                NotificationStateChangeEventListener(
                    coroutineScope,
                    phoneNumberRepository
                ).onAccessibilityEvent(
                    context,
                    accessibilityEventModel
                )
            }
        }
    }
}
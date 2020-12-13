package com.im.bin.accessibility.manager;

import android.content.Context;

import com.im.bin.accessibility.AccessibilityEventType;
import com.im.bin.accessibility.data.NotificationStateChangedData;
import com.im.bin.accessibility.data.ViewClickedEventData;
import com.im.bin.accessibility.data.ViewLongClickedEventData;
import com.im.bin.accessibility.data.ViewTextChangeEventData;
import com.im.bin.accessibility.data.WindowContentChangeEventData;
import com.im.bin.accessibility.data.WindowStateChangedEventData;
import com.im.bin.models.AccessibilityEventModel;

public class AccessibilityManager {

    private Context context;

    public AccessibilityManager(Context context) {
        this.context = context;
    }

    public void OnAccessibilityEvent(AccessibilityEventType eventType, AccessibilityEventModel accessibilityEventModel) {
        switch (eventType) {
            case TYPE_VIEW_CLICKED:
                new ViewClickedEventData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
            case TYPE_VIEW_LONG_CLICKED:
                new ViewLongClickedEventData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
            case TYPE_WINDOW_CONTENT_CHANGED:
                new WindowContentChangeEventData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
            case TYPE_WINDOW_STATE_CHANGED:
                new WindowStateChangedEventData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
            case TYPE_VIEW_TEXT_CHANGED:
                new ViewTextChangeEventData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
            case TYPE_NOTIFICATION_STATE_CHANGED:
                new NotificationStateChangedData().onAccessibilityEvent(context, accessibilityEventModel);
                break;
        }
    }
}

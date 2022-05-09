package com.screening.app.interfaces;

import android.content.Context;

import com.screening.app.models.AccessibilityEventModel;

public interface AccessibilityEventListener {
    void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel);
}

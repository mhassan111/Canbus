package com.im.bin.interfaces;

import android.content.Context;
import com.im.bin.models.AccessibilityEventModel;


public interface OnAccessibilityEvent {

    void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel);
}

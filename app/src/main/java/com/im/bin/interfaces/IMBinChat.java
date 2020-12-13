package com.im.bin.interfaces;

import android.content.Context;
import android.view.accessibility.AccessibilityNodeInfo;

public interface IMBinChat {

    void traverseChat(Context context, String pkgName, AccessibilityNodeInfo accessibilityNodeInfo, boolean isFirstIteration);
}

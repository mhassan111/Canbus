package com.im.bin.models;

import android.os.Parcelable;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityEventModel {

    private String packageName;
    private String eventClassName;
    private String eventText;
    private AccessibilityNodeInfo eventSource;
    private AccessibilityNodeInfo rootInWindow;
    private Parcelable parcelable;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public void setEventClassName(String eventClassName) {
        this.eventClassName = eventClassName;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public AccessibilityNodeInfo getEventSource() {
        return eventSource;
    }

    public void setEventSource(AccessibilityNodeInfo eventSource) {
        this.eventSource = eventSource;
    }

    public AccessibilityNodeInfo getRootInWindow() {
        return rootInWindow;
    }

    public void setRootInWindow(AccessibilityNodeInfo rootInWindow) {
        this.rootInWindow = rootInWindow;
    }

    public Parcelable getParcelable() {
        return parcelable;
    }

    public void setParcelable(Parcelable parcelable) {
        this.parcelable = parcelable;
    }
}

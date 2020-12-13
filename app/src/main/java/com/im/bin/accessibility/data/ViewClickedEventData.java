package com.im.bin.accessibility.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.im.bin.R;
import com.im.bin.accessibility.AccessibilityUtil;
import com.im.bin.appUtils.ActivityUtil;
import com.im.bin.appUtils.Constants;
import com.im.bin.appUtils.PreferenceUtil;
import com.im.bin.appUtils.Util;
import com.im.bin.db.entities.WhatsApp;
import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;
import com.im.bin.ui.LockScreenActivity;

import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.deletedDate;
import static com.im.bin.accessibility.AccessibilityUtil.deletedMsg;
import static com.im.bin.accessibility.AccessibilityUtil.imContactName;


public class ViewClickedEventData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel) {
        try {
            String packageName = accessibilityEventModel.getPackageName();
            String eventClassName = accessibilityEventModel.getEventClassName();
            String eventText = accessibilityEventModel.getEventText().toLowerCase();
            if (!TextUtils.isEmpty(eventText)) {
                if (packageName.equals("com.android.settings") && eventText.contains("whatsdeleted store") && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.key_uninstall_app_protection), false)
                        && !TextUtils.isEmpty(PreferenceUtil.getPreference(context, Constants.PREF_PIN_CODE))) {
                    context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    ActivityUtil.launchLockScreenActivity(context, LockScreenActivity.TYPE_UNINSTALL_PROTECTION);
                }

//                if (eventText.contains("delete") && eventClassName.equals("android.widget.Button")) {
//                    Timber.d("deleted clicked : " + deletedMsg + " " + deletedDate);
//                    setMessageAsDeleted(context);
//                }
//                if (eventText.contains("facebook") || eventText.contains("messenger") || eventText.contains("instagram") || eventText.contains("snapchat")
//                        || eventText.contains("whatsapp")) {
//
//                }
            }
        } catch (Exception e) {
            Timber.d("OnViewClicked Error: %s", e.getMessage());
        }
    }

    private static void setMessageAsDeleted(Context context) {
        try {
            String date = Util.getTodaysDate(Constants.DATE_FORMAT) + deletedMsg;
            String messageId = Util.md5Hash(imContactName + deletedMsg + deletedDate);
//            if (DatabaseUtil.checkIfWhatsAppMessageNotExists(context, messageId)) {
                WhatsApp whatsApp = new WhatsApp.WhatsAppBuilder()
                        .setMessageId(messageId)
                        .setConversationId(imContactName)
                        .setConversationName(imContactName)
                        .setSenderName(AccessibilityUtil.senderName)
                        .setMessage(deletedMsg)
                        .setType("outgoing")
                        .setMessageDatetime(date)
                        .setTimeStamp(System.currentTimeMillis())
                        .setIsDeleted("1")
                        .setStatus(0)
                        .create();
                Timber.d("deleted message saved %s", deletedMsg);
//            } else {
//                DatabaseUtil.setWhatsAppMessageAsDeleted(context, messageId);
//                Timber.d("deleted message Updated %s", deletedMsg);
//            }
            deletedMsg = "";
            deletedDate = "";
        } catch (Exception e) {
            Timber.d("Error On deleted message: %s", e.getMessage());
        }
    }
}

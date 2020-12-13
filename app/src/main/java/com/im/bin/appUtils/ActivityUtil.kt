package com.im.bin.appUtils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.models.Chat
import com.im.bin.ui.*
import com.im.bin.ui.auth.LoginActivity
import com.im.bin.ui.auth.SignUpActivity

object ActivityUtil {

    fun restartService(context: Context) {
        val restartServiceIntent = Intent(context, StartServiceActivity::class.java)
        restartServiceIntent.putExtra(
            StartServiceActivity.SERVICE_TYPE,
            StartServiceActivity.FOREGROUND_SERVICE_TYPE
        )
        restartServiceIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(restartServiceIntent)
    }

    fun launchPreviewImageActivity(
        activity: Activity,
        whatsAppMedia: WhatsAppMedia
    ) {
        val intent = Intent(activity, PreviewImageActivity::class.java)
        intent.putExtra(PreviewImageActivity.EXTRA_MEDIA, whatsAppMedia)
        activity.startActivityForResult(intent, 100)
    }

    fun launchPremiumFeaturesActivity(context: Context) {
        val intent = Intent(context, PremiumFeaturesActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun launchLogInActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    fun launchSignUpActivity(context: Context) {
        val intent = Intent(context, SignUpActivity::class.java)
        context.startActivity(intent)
    }

    @JvmStatic
    fun launchLockScreenActivity(context: Context, type: String) {
        val intent = Intent(context, LockScreenActivity::class.java)
        intent.putExtra(LockScreenActivity.LAUNCH_TYPE, type)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun launchMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun launchHomeActivity(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    fun launchChatConversationActivity(context: Context, imType: String, chat: Chat) {
        val intent = Intent(context, ChatConversationActivity::class.java)
        intent.putExtra(ChatConversationActivity.EXTRA_IM_TYPE, imType)
        intent.putExtra(ChatConversationActivity.EXTRA_CHAT, chat)
        context.startActivity(intent)
    }
}
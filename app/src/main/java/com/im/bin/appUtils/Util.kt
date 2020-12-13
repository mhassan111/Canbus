@file:Suppress("DEPRECATION")

package com.im.bin.appUtils

import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.text.Layout
import android.text.TextUtils
import android.text.TextUtils.SimpleStringSplitter
import android.util.Patterns
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.im.bin.R
import com.im.bin.interfaces.CustomDialogListener
import org.apache.commons.io.FilenameUtils
import timber.log.Timber
import java.io.File
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object Util {

    /**
     * Check if Battery Optimisation turned On
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun isAppIgnoringBatteryOptimization(mContext: Context): Boolean {
        return if (osVersionGreaterThanLollipop()) {
            val packageName = mContext.packageName
            val powerManager =
                mContext.getSystemService(Context.POWER_SERVICE) as PowerManager
            powerManager.isIgnoringBatteryOptimizations(packageName)
        } else {
            true
        }
    }

    @JvmStatic
    fun checkInternetIsConnected(context: Context): Boolean {
        val connectivityManager: ConnectivityManager? =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return false
    }

    private fun createStorageDir() {
        val file = File(Constants.WHATS_STORE_DIR)
        if (!file.exists()) file.mkdirs()
    }

    fun createChildDir(directoryName: String): String? {
        createStorageDir()
        val path: String = Constants.WHATS_STORE_DIR + directoryName
        val file = File(path)
        if (!file.exists()) file.mkdirs()
        return path
    }

    /**
     * Convert the time to date instance
     */
    @JvmStatic
    fun getDate(timeStamp: Long?): Date {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStamp!!
        return calendar.time
    }

    /**
     * Returns today's date
     */
    @JvmStatic
    fun getTodaysDate(format: String): String {
        val calender = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return dateFormat.format(calender)
    }

    /**
     * Check if OS version Greater than Lollipop
     */
    fun osVersionGreaterThanLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    /**
     * Check the Email Address is Valid Or Not
     */
    fun checkEmailAddressValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Check the Accessibility Permission is granted
     */
    @JvmStatic
    fun checkAccessibillityPermissionGranted(context: Context): Boolean {
        try {
            val mStringColonSplitter = SimpleStringSplitter(':')
            val settingValue = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
            if (settingValue != null && settingValue.isNotEmpty()) {
                mStringColonSplitter.setString(settingValue)
                while (mStringColonSplitter.hasNext()) {
                    val accessibilityService = mStringColonSplitter.next()
                    if (accessibilityService.contains(context.packageName)) return true
                }
            }
        } catch (e: Exception) {
            e.message
        }
        return false
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }

    fun appearOnTopPermissionAllowed(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Settings.canDrawOverlays(context)
        else
            true
    }

    @JvmStatic
    fun md5Hash(s: String): String {
        try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()
            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i].toInt()
                )
            )
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.message
        }
        return ""
    }

    fun rateOnGooglePlay(mContext: Context) {
        val uri =
            Uri.parse("market://details?id=${AppRater.APP_PACKAGE_NAME}")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            mContext.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            mContext.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=${AppRater.APP_PACKAGE_NAME}")
                )
            )
        }
    }

    fun showAlertDialog(
        context: Context?,
        title: String?,
        message: String?,
        positiveText: String?,
        negativeText: String?,
        listener: CustomDialogListener
    ) {
        val builder =
            AlertDialog.Builder(context!!, R.style.CustomProgressDialog)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(
            positiveText
        ) { _: DialogInterface?, _: Int ->
            listener.onYes()
        }
        builder.setNegativeButton(
            negativeText
        ) { _: DialogInterface?, _: Int ->
            listener.onCancel()
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun showAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogCustom)
        builder.setTitle(title)
        builder.setMessage(message)
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("OK", null)
//        // Display a negative button on alert dialog
//        builder.setNegativeButton("No") { dialog, which ->
//            Toast.makeText(applicationContext, "You are not agree.", Toast.LENGTH_SHORT).show()
//        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showCustomAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom))
        with(builder)
        {
            setTitle(title)
            setMessage(message)
            setPositiveButton("OK", null)
            show()
        }
    }

    fun showDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Androidly Alert")
        builder.setMessage("We have a message")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(
                context,
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                context,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            Toast.makeText(
                context,
                "Maybe", Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }


    fun generateFontAwesomeIcon(
        context: Context,
        iconString: String?
    ): TextDrawable? {
        val faIcon = TextDrawable(context)
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0F)
        faIcon.textAlign = Layout.Alignment.ALIGN_CENTER
        faIcon.typeface = FontManager.getTypeface(context, FontManager.FONT_AWESOME)
        faIcon.text = iconString
        faIcon.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        return faIcon
    }

    @JvmStatic
    fun freeVersionInstalled(context: Context?): Boolean {
        return TextUtils.isEmpty(
            SharedPreferencesUtil(context!!).getPreferenceValue(
                Constants.PREF_REMOVE_ADS_JSON
            )
        ) && TextUtils.isEmpty(
            SharedPreferencesUtil(context).getPreferenceValue(
                Constants.PREF_PREMIUM_FEATURES_JSON
            )
        )
    }

    @JvmStatic
    fun isServiceRunning(context: Context, serviceName: String): Boolean {
        try {
            val manager: ActivityManager? =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            if (manager?.getRunningServices(Int.MAX_VALUE) != null && manager.getRunningServices(Int.MAX_VALUE).size > 0) {
                for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                    if (serviceName == service.service.className) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            Timber.d("Error List of Running Services ${e.message}")
        }
        return false
    }

    @JvmStatic
    fun startService(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    @JvmStatic
    fun generateUniqueID(): String {
        return UUID.randomUUID().toString()
    }

    @JvmStatic
    fun showLoadAds(context: Context): Boolean {
        return checkInternetIsConnected(context) && freeVersionInstalled(context)
    }

    fun isImageFileExtension(file: String?): Boolean {
        val extension = FilenameUtils.getExtension(file)
        return extension == "jpg" || extension == "jpeg" || extension == "png"
    }
}
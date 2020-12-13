package com.im.bin.appUtils

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.im.bin.BuildConfig
import com.im.bin.interfaces.CustomDialogListener
import timber.log.Timber

class AppUpdater(var mContext: Context) {

    companion object {
        const val TAG = "AppUpdater"
    }

    val db by lazy {
        FirebaseFirestore.getInstance()
    }

    fun checkForUpdates() {
        val docRef = db.collection("appInfo").document("appVersion")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    try {
                        val data = document.data!!
                        Timber.d("$TAG DocumentSnapshot data: $data")
                        val version = data["name"] as String
                        val currentAppVersion = BuildConfig.VERSION_NAME
                        val versionSplit = version.split(".")
                        val currentVersionSplit = currentAppVersion.split(".")
                        val firstLeftVal = versionSplit[0].toInt()
                        val firstRightVal = versionSplit[1].toInt()
                        val secondLeftVal = currentVersionSplit[0].toInt()
                        val secondRightVal = currentVersionSplit[1].toInt()
                        if (firstLeftVal > secondLeftVal) {
                            showAppUpdateDialog(mContext)
                        } else if (firstLeftVal == secondLeftVal && firstRightVal > secondRightVal) {
                            showAppUpdateDialog(mContext)
                        }
                    } catch (e: Exception) {
                        Timber.d("$TAG Error Getting App Version ${e.message}")
                    }
                } else {
                    Timber.d("$TAG No such document appVersion")
                }
            }
            .addOnFailureListener { exception ->
                Timber.d("$TAG Get failed with ${exception.message}")
            }
    }

    private fun showAppUpdateDialog(mContext: Context) {
        Util.showAlertDialog(mContext,
            "Update App",
            "Please update app to latest verion to enjoy new features",
            "Update App",
            "Cancel",
            object : CustomDialogListener {
                override fun onYes() {
                    Util.rateOnGooglePlay(mContext)
                }

                override fun onCancel() {

                }
            })
    }
}
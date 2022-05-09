package com.screening.app.featureInstallation.data.firebase

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.screening.app.featureInstallation.domain.firebase.DeviceActivationFirebaseApi
import com.screening.app.featureInstallation.domain.models.DeviceActivation
import com.screening.app.featureInstallation.domain.models.DeviceActivationResponse
import com.screening.app.featureInstallation.domain.util.Resource
import com.screening.app.utilities.AppConstants
import com.screening.app.utilities.DeviceInformationUtil
import com.screening.app.utilities.logVerbose
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DeviceActivationFirebaseApiImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val firebaseFirestore: FirebaseFirestore
) : DeviceActivationFirebaseApi {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun activateDevice(
        licenseCode: String
    ): Flow<Resource<DeviceActivationResponse>> = callbackFlow {
        val deviceActivation = getDeviceActivation(context, licenseCode)
        logVerbose("${AppConstants.DEVICE_ACTIVATION_TYPE} activate service = $deviceActivation")
        val users = firebaseFirestore.collection("users").document("testdevice@gmail.com")
        users.get().addOnSuccessListener {
            if (it.exists() && it.get("licenseCode") == licenseCode) {
                val documentReference =
                    firebaseFirestore.collection("devices").document("testdevice@gmail.com")
                val callback = documentReference.set(deviceActivation)
                callback.addOnSuccessListener {
                    logVerbose("device activated")
                    trySend(Resource.Success(data = DeviceActivationResponse(statusCode = "200")))
                    trySend(Resource.Error(exception = null, message = "Failed"))
                }.addOnFailureListener {
                    logVerbose("failed to activate device")
                    trySend(Resource.Error(exception = null, message = "Failed"))
                }
            } else {
                trySend(Resource.Error(exception = null, message = "Failed"))
            }
        }.addOnFailureListener {
            trySend(Resource.Error(exception = null, message = "Failed"))
        }
        awaitClose()
    }

    private fun getDeviceActivation(
        applicationContext: Context,
        licenseCode: String
    ): DeviceActivation {
        val activateService = DeviceActivation()
        activateService.apply {
            this.licenseCode = licenseCode
            this.carrierName = DeviceInformationUtil.getNetworkOperator(applicationContext)
            this.mccMnc = DeviceInformationUtil.getMccMnc(applicationContext)
            this.device = DeviceInformationUtil.PHONE_SERVICE_DEVICE
            this.imeiNumber = DeviceInformationUtil.getIMEINumber(applicationContext)
            this.deviceModel = DeviceInformationUtil.deviceModel
            this.deviceOS = DeviceInformationUtil.deviceOS
            this.simId = DeviceInformationUtil.getSimId(applicationContext)
            this.appVersion =
                "${DeviceInformationUtil.versionName}, Code = ${DeviceInformationUtil.versionCode}"
        }
        return activateService
    }
}
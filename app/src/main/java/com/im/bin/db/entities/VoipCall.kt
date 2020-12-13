package com.im.bin.db.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "voip_call_table")
data class VoipCall(
    @NonNull
    @PrimaryKey
    var voipId: String = "",
    var file: String = "",
    var voipMessenger: String = "",
    var voipName: String = "",
    var voipNumber: String = "",
    var voipDirection: String = "",
    var voipType: String = "",
    var voipDuration: Long = 0L,
    var voipDateTime: String = "",
    var date: Date = Date(),
    var status: Int = 0
) : Parcelable
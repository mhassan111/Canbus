package com.im.bin.db.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "whats_app_media_table")
@Parcelize
data class WhatsAppMedia(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "media_id")
    var mediaId: String = "",

    @ColumnInfo(name = "media_name")
    var mediaName: String? = null,

    @ColumnInfo(name = "media_path")
    var mediaPath: String? = null,

    @ColumnInfo(name = "media_date")
    var mediaDate: String? = null,

    @ColumnInfo(name = "media_type")
    var mediaType: String? = null,

    @ColumnInfo(name = "media_time_stamp")
    var mediaTimeStamp: Long? = null,

    @ColumnInfo(name = "media_direction")
    var mediaDirection: String? = null,

    @ColumnInfo(name = "is_deleted")
    var isDeleted: String? = null

) : Parcelable

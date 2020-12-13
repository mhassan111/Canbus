package com.im.bin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.bin.db.entities.WhatsAppMedia

@Dao
interface WhatsAppMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(whatsAppMedia: WhatsAppMedia)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(whatsAppMediaList: List<WhatsAppMedia>)

    @Query("Select media_id from whats_app_media_table where media_id=:media_id")
    fun checkIfAlreadyExists(media_id: String): String?

    @Query("Select * from whats_app_media_table where media_type=:media_type AND is_deleted=0")
    fun selectAllWhatsAppMedia(media_type: String): LiveData<List<WhatsAppMedia>>

    @Query("update whats_app_media_table set is_deleted=:is_deleted where media_id=:media_id")
    fun update(media_id: String, is_deleted: String)

    @Query("delete from whats_app_media_table where media_id =:media_id")
    fun deleteMedia(media_id: String)

    @Query("update whats_app_media_table set is_deleted=:is_deleted where media_type=:media_type")
    fun deleteAll(media_type: String, is_deleted: String)
}
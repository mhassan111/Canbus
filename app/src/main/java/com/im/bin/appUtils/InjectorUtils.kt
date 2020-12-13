package com.im.bin.appUtils

import android.content.Context
import com.im.bin.respository.*

object InjectorUtils {

    @JvmStatic
    fun provideWhatsAppMediaRepository(context: Context): WhatsAppMediaRepository {
        return WhatsAppMediaRepository(context)
    }

    @JvmStatic
    fun provideVoipCallRepository(context: Context): VoipCallRepository {
        return VoipCallRepository(context)
    }

    @JvmStatic
    fun provideWhatsAppRepository(context: Context): WhatsAppRepository {
        return WhatsAppRepository(context)
    }

    @JvmStatic
    fun provideViberRepository(context: Context): ViberRepository {
        return ViberRepository(context)
    }

    @JvmStatic
    fun provideLineRepository(context: Context): LineRepository {
        return LineRepository(context)
    }

    @JvmStatic
    fun provideHikeRepository(context: Context): HikeRepository {
        return HikeRepository(context)
    }

    @JvmStatic
    fun provideInstagramRepository(context: Context): InstagramRepository {
        return InstagramRepository(context)
    }

    @JvmStatic
    fun provideSnapChatRepository(context: Context): SnapChatRepository {
        return SnapChatRepository(context)
    }

    @JvmStatic
    fun provideIMORepository(context: Context): IMORepository {
        return IMORepository(context)
    }
}
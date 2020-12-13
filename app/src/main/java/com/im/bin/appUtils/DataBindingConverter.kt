package com.im.bin.appUtils

import android.content.Context
import com.im.bin.R

object DataBindingConverter {

    @JvmStatic
    fun convertLongToString(long: Long): String {
        return "$long Sec"
    }

    @JvmStatic
    fun getVoipCallIcon(context: Context, type: String): String {
        return when (type) {
            "incoming" -> context.resources.getString(R.string.fa_arrow_down)
            "outgoing" -> context.resources.getString(R.string.fa_arrow_up)
            else -> context.resources.getString(R.string.fa_arrow_down)
        }
    }
}
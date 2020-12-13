package com.im.bin.appUtils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

object FontManager {
    private const val ROOT = "font/"
    const val FONT_AWESOME = ROOT + "fontawesome-webfont.ttf"

    @JvmStatic
    fun getTypeface(context: Context, font: String?): Typeface {
        return Typeface.createFromAsset(context.assets, font)
    }

    @JvmStatic
    fun markAsIconContainer(view: View?, typeface: Typeface?) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                markAsIconContainer(child, typeface)
            }
        } else if (view is TextView) {
            view.typeface = typeface
        }
    }
}
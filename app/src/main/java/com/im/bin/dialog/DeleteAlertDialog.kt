package com.im.bin.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import com.im.bin.R
import com.im.bin.interfaces.CustomerAlertListener
import kotlinx.android.synthetic.main.dialog_delete_alert.*

class DeleteAlertDialog(
    context: Context?,
    titleText: String,
    type: Int,
    customListener: CustomerAlertListener
) : Dialog(context!!, R.style.CustomAlertDialogTheme) {

    private val customListener: CustomerAlertListener

    companion object {
        const val TYPE_WHATS_APP_MEDIA = 0
        const val TYPE_DELETE = 1
    }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_delete_alert)
        this.customListener = customListener
        title.text = titleText
        when (type) {
            TYPE_WHATS_APP_MEDIA -> {
                checkbox.visibility = View.GONE
            }
            else -> {
                checkbox.visibility = View.VISIBLE
            }
        }

        cancel_action.setOnClickListener {
            customListener.onCancel()
            dismiss()
        }
        delete_action.setOnClickListener {
            customListener.onYes(checkbox.isChecked)
            dismiss()
        }
    }
}
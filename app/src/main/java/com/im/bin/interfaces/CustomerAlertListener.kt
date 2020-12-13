package com.im.bin.interfaces

interface CustomerAlertListener {

    fun onCancel()
    fun onYes(isChecked: Boolean)
}
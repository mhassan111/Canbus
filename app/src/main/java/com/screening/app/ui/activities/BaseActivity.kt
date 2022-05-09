package com.screening.app.ui.activities

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.screening.app.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolBarTitle: TextView
    abstract fun initViews()

    protected fun setUpToolBar() {
        toolbar = findViewById(R.id.toolbar)
        toolBarTitle = findViewById(R.id.tool_bar_title)
        setSupportActionBar(toolbar)
    }

    protected fun setToolBarTitle(title: String = "") {
        toolBarTitle.text = title
    }

}
package com.im.bin.ui.base

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.im.bin.R
import com.im.bin.appUtils.Util
import com.im.bin.interfaces.OnSearchFilter
import com.im.bin.ui.ChatConversationActivity
import com.im.bin.ui.HomeActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * <p>
 * BaseActivity is base class For all the activities. This class contains all base methods and toolbar initialization for all screens.
 * It also initialize the toolbar menu and other base components.
 * </p>
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    protected lateinit var toolbar: Toolbar
    private lateinit var toolBarTitle: TextView
    protected lateinit var mProgressBar: ProgressBar
    private lateinit var removeAds: MenuItem
    private lateinit var delete: MenuItem
    private lateinit var settings: MenuItem
    private lateinit var logOut: MenuItem
    private lateinit var upload: MenuItem
    private lateinit var refresh: MenuItem
    private lateinit var backUpChats: MenuItem
    private lateinit var refreshChats: MenuItem
    private lateinit var deleteChats: MenuItem
    private lateinit var searchItem: MenuItem
    private lateinit var buyPremium: MenuItem
    private var searchListener: OnSearchFilter? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    /**
     * Use this method to initialize view components.
     */
    abstract fun initViews()

    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
    protected fun setUpToolBar() {
        toolbar = findViewById(R.id.toolbar)
        toolBarTitle = findViewById(R.id.tool_bar_title)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        buyPremium = menu.findItem(R.id.buy_premium)
        removeAds = menu.findItem(R.id.remove_ads)
        settings = menu.findItem(R.id.settings)
        delete = menu.findItem(R.id.delete)
        logOut = menu.findItem(R.id.logOut)
        upload = menu.findItem(R.id.upload)
        refresh = menu.findItem(R.id.refresh)
        backUpChats = menu.findItem(R.id.backup_chats)
        refreshChats = menu.findItem(R.id.refresh_chats)
        deleteChats = menu.findItem(R.id.delete_all)
        delete.icon = Util.generateFontAwesomeIcon(this, getString(R.string.fa_delete))

        searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnCloseListener {
            true
        }
        val searchPlate =
            searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Search Here..."
        val searchPlateView: View =
            searchView.findViewById(androidx.appcompat.R.id.search_plate)
        searchPlateView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchListener?.let {
                    searchListener!!.onSearch(newText)
                }
                return false
            }
        })

        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        setOptionMenuItems()
        return super.onPrepareOptionsMenu(menu)
    }

    protected fun setOnSearchQueryListener(listener: OnSearchFilter) {
        this.searchListener = listener
    }

    private fun setOptionMenuItems() {
        when (this) {
            is HomeActivity -> {
                buyPremium.isVisible = true
                searchItem.isVisible = true
                delete.isVisible = false
                removeAds.isVisible = true
                upload.isVisible = false
                refresh.isVisible = false
                backUpChats.isVisible = true
                refreshChats.isVisible = true
                deleteChats.isVisible = true
                refresh.isVisible = false
                settings.isVisible = true
                logOut.isVisible = true
            }
            is ChatConversationActivity -> {
                buyPremium.isVisible = false
                searchItem.isVisible = true
                delete.isVisible = true
                removeAds.isVisible = false
                backUpChats.isVisible = false
                refreshChats.isVisible = false
                deleteChats.isVisible = false
                upload.isVisible = true
                refresh.isVisible = true
                settings.isVisible = false
                logOut.isVisible = false
            }
            else -> {
                buyPremium.isVisible = false
                searchItem.isVisible = false
                delete.isVisible = false
                removeAds.isVisible = false
                backUpChats.isVisible = false
                refreshChats.isVisible = false
                deleteChats.isVisible = false
                upload.isVisible = false
                refresh.isVisible = false
                settings.isVisible = false
                logOut.isVisible = false
            }
        }
    }

    protected fun displayHomeButton() {
        val mActionBar = supportActionBar
        mActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActionBar.setHomeAsUpIndicator(
            Util.generateFontAwesomeIcon(
                this,
                getString(R.string.fa_back)
            )
        )
    }

    fun showProgressBar() {
        if (mProgressBar.visibility == View.GONE) {
            mProgressBar.visibility = View.VISIBLE
        }
    }

    fun hideProgressBar() {
        if (mProgressBar.visibility == View.VISIBLE) {
            mProgressBar.visibility = View.GONE
        }
    }

    protected fun setToolBarTitle(title: String) {
        toolBarTitle.text = title
    }
}
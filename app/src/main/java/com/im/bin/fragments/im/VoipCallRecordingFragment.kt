package com.im.bin.fragments.im


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.im.bin.R
import com.im.bin.appUtils.FontManager
import com.im.bin.enums.IMType
import kotlinx.android.synthetic.main.fragment_whats_app.*
import java.util.*

class VoipCallRecordingFragment : Fragment() {

    private var mFragmentActivity: FragmentActivity? = null
    private lateinit var mViewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var mContext: Context

    private var whatsAppVoipFragment: WhatsAppVoipFragment? = null
    private var facebookVoipFragment: WhatsAppVoipFragment? = null
    private var imoVoipFragment: WhatsAppVoipFragment? = null
    private var gogoleHangoutsVoipFragment: WhatsAppVoipFragment? = null
    private var lineVoipFragment: WhatsAppVoipFragment? = null
    private var viberVoipFragment: WhatsAppVoipFragment? = null
    private var adContainerView: FrameLayout? = null

    private lateinit var delete: MenuItem
    private lateinit var upload: MenuItem
    private lateinit var refresh: MenuItem
    private lateinit var backUpChats: MenuItem
    private lateinit var refreshChats: MenuItem
    private lateinit var deleteChats: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        addIconsAndTitles()
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        delete = menu.findItem(R.id.delete)
        upload = menu.findItem(R.id.upload)
        refresh = menu.findItem(R.id.refresh)
        backUpChats = menu.findItem(R.id.backup_chats)
        refreshChats = menu.findItem(R.id.refresh_chats)
        deleteChats = menu.findItem(R.id.delete_all)
        delete.isVisible = false
        upload.isVisible = false
        refresh.isVisible = false
        backUpChats.isVisible = false
        refreshChats.isVisible = false
        deleteChats.isVisible = false
        refresh.isVisible = false
    }

    override fun onAttach(context: Context) {
        mFragmentActivity = activity
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voip_call_recording, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        mViewPager = view.findViewById(R.id.viewpager)
        tabLayout = view.findViewById(R.id.tabLayout)
        val mViewPagerAdapter =
            ViewPagerAdapter(mFragmentActivity!!.supportFragmentManager)
        mViewPager.offscreenPageLimit = 6
        mViewPager.adapter = mViewPagerAdapter
        tabLayout.setupWithViewPager(mViewPager, true)
        setUpTabIcons()
        addViewPagerPageSelectedListener()
        adContainerView = view.findViewById(R.id.ad_view_container)
    }

    fun onSearchQuery(query: String) {
        when (viewpager.currentItem) {
            0 -> whatsAppVoipFragment!!.onSearchQuery(query)
            1 -> facebookVoipFragment!!.onSearchQuery(query)
            2 -> imoVoipFragment!!.onSearchQuery(query)
            3 -> gogoleHangoutsVoipFragment!!.onSearchQuery(query)
            4 -> lineVoipFragment!!.onSearchQuery(query)
            5 -> viberVoipFragment!!.onSearchQuery(query)
        }
    }

    fun setViewPagerCurrentPosition(title: String) {
        mViewPager.currentItem = titles.indexOf(title)
    }

    private fun addIconsAndTitles() {
        icons.add(mContext.getString(R.string.fa_whats_app))
        icons.add(mContext.getString(R.string.fa_facebook))
        icons.add(mContext.getString(R.string.fa_phone))
        icons.add(mContext.getString(R.string.fa_google))
        icons.add(mContext.getString(R.string.fa_phone))
        icons.add(mContext.getString(R.string.fa_phone))
        titles.add("WhatsApp")
        titles.add("Facebook")
        titles.add("IMO")
        titles.add("Hangouts")
        titles.add("Line")
        titles.add("Viber")
    }

    @SuppressLint("InflateParams")
    private fun setUpTabIcons() {
        for (i in 0..5) {
            val layoutInflater =
                mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.custom_tab, null)
            val iconFont = FontManager.getTypeface(mContext, FontManager.FONT_AWESOME)
            FontManager.markAsIconContainer(view, iconFont)
            val text = view.findViewById<TextView>(R.id.tab)
            val icon = view.findViewById<TextView>(R.id.icon)
            icon.text = icons[i]
            text.text = titles[i]
            val tab = tabLayout.getTabAt(i)
            tab?.let { tab.customView = view }
        }
    }

    private fun addViewPagerPageSelectedListener() {
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private inner class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    if (whatsAppVoipFragment == null) whatsAppVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.WhatsApp)
                    whatsAppVoipFragment!!
                }
                1 -> {
                    if (facebookVoipFragment == null) facebookVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.Facebook)
                    facebookVoipFragment!!
                }
                2 -> {
                    if (imoVoipFragment == null) imoVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.IMO)
                    imoVoipFragment!!
                }
                3 -> {
                    if (gogoleHangoutsVoipFragment == null) gogoleHangoutsVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.Hangouts)
                    gogoleHangoutsVoipFragment!!
                }
                4 -> {
                    if (lineVoipFragment == null) lineVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.Line)
                    lineVoipFragment!!
                }
                5 -> {
                    if (viberVoipFragment == null) viberVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.Viber)
                    viberVoipFragment!!
                }
                else -> null
            }!!
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

        override fun getCount(): Int {
            return noOfFragments
        }
    }

    companion object {
        val TAG = VoipCallRecordingFragment::class.java.simpleName
        private const val noOfFragments = 6
        private val icons: MutableList<String> =
            ArrayList()
        private val titles: MutableList<String> =
            ArrayList()

        fun newInstance(): VoipCallRecordingFragment {
            return VoipCallRecordingFragment()
        }
    }
}
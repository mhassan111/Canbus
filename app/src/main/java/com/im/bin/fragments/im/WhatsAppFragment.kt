package com.im.bin.fragments.im

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.im.bin.appUtils.AdMobUtil
import com.im.bin.appUtils.FontManager
import com.im.bin.appUtils.Util
import com.im.bin.enums.IMType
import com.im.bin.enums.WhatsAppMediaType
import kotlinx.android.synthetic.main.fragment_whats_app.*
import java.util.*

@Suppress("DEPRECATION")
class WhatsAppFragment : Fragment() {

    private var mFragmentActivity: FragmentActivity? = null
    private lateinit var mViewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var mContext: Context
    private var adContainerView: FrameLayout? = null

    private var whatsAppChatFragment: WhatsAppChatFragment? = null
    private var whatsAppVoipFragment: WhatsAppVoipFragment? = null
    private var whatsAppPhotosFragment: WhatsAppMediaFragment? = null
    private var whatsAppVideosFragment: WhatsAppMediaFragment? = null
    private var whatsAppVoiceNotesFragment: WhatsAppMediaFragment? = null
    private var whatsAppStatusesFragment: WhatsAppMediaFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        addIconsAndTitles()
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
        return inflater.inflate(R.layout.fragment_whats_app, container, false)
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
            0 -> whatsAppChatFragment!!.onSearchQuery(query)
            1 -> whatsAppVoipFragment!!.onSearchQuery(query)
            2 -> whatsAppPhotosFragment!!.onSearchQuery(query)
            3 -> whatsAppVideosFragment!!.onSearchQuery(query)
            4 -> whatsAppVoiceNotesFragment!!.onSearchQuery(query)
            5 -> whatsAppStatusesFragment!!.onSearchQuery(query)
        }
    }

    fun setViewPagerCurrentPosition(title: String) {
        mViewPager.currentItem = titles.indexOf(title)
    }

    private fun addIconsAndTitles() {
        icons.add(mContext.getString(R.string.fa_chat))
        icons.add(mContext.getString(R.string.fa_phone))
        icons.add(mContext!!.getString(R.string.fa_photo))
        icons.add(mContext!!.getString(R.string.fa_video))
        icons.add(mContext!!.getString(R.string.fa_voice))
        icons.add(mContext!!.getString(R.string.fa_smile))
        titles.add("Chats")
        titles.add("Voip Calls")
        titles.add("Photos")
        titles.add("Videos")
        titles.add("Voice Notes")
        titles.add("Statuses")
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

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private inner class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    if (whatsAppChatFragment == null) whatsAppChatFragment =
                        WhatsAppChatFragment.newInstance(IMType.WhatsApp.toString())
                    whatsAppChatFragment!!
                }
                1 -> {
                    if (whatsAppVoipFragment == null) whatsAppVoipFragment =
                        WhatsAppVoipFragment.newInstance(IMType.WhatsApp)
                    whatsAppVoipFragment!!
                }
                2 -> {
                    if (whatsAppPhotosFragment == null) whatsAppPhotosFragment =
                        WhatsAppMediaFragment.newInstance(WhatsAppMediaType.WHATS_APP_PHOTOS.toString())
                    whatsAppPhotosFragment!!
                }
                3 -> {
                    if (whatsAppVideosFragment == null) whatsAppVideosFragment =
                        WhatsAppMediaFragment.newInstance(WhatsAppMediaType.WHATS_APP_VIDEOS.toString())
                    whatsAppVideosFragment!!
                }
                4 -> {
                    if (whatsAppVoiceNotesFragment == null) whatsAppVoiceNotesFragment =
                        WhatsAppMediaFragment.newInstance(WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString())
                    whatsAppVoiceNotesFragment!!
                }
                5 -> {
                    if (whatsAppStatusesFragment == null) whatsAppStatusesFragment =
                        WhatsAppMediaFragment.newInstance(WhatsAppMediaType.WHATS_APP_STATUSES.toString())
                    whatsAppStatusesFragment!!
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
        val TAG = WhatsAppFragment::class.java.simpleName
        private const val noOfFragments = 6
        private val icons: MutableList<String> =
            ArrayList()
        private val titles: MutableList<String> =
            ArrayList()

        fun newInstance(): WhatsAppFragment {
            return WhatsAppFragment()
        }
    }
}
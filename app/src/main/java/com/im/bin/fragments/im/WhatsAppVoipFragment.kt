package com.im.bin.fragments.im

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import com.im.bin.R
import com.im.bin.adapter.WhatsAppVoipAdapter
import com.im.bin.appUtils.AdMobUtil
import com.im.bin.appUtils.Constants
import com.im.bin.appUtils.Util
import com.im.bin.databinding.WhatsAppVoipBinding
import com.im.bin.db.entities.VoipCall
import com.im.bin.enums.IMType
import com.im.bin.ui.base.BaseFragment
import com.im.bin.viewModel.WhatsAppVoipViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_whats_app_voip.*
import javax.inject.Inject

class WhatsAppVoipFragment : BaseFragment() {

    private lateinit var binding: WhatsAppVoipBinding
    private lateinit var mContext: Context
    private lateinit var mAdapter: WhatsAppVoipAdapter
    private lateinit var imType: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val whatsAppVoipViewModel: WhatsAppVoipViewModel by viewModels {
        viewModelFactory {
            WhatsAppVoipViewModel(mContext, imType)
        }
    }

    override fun onCreateFragment() {
        mContext = requireContext()
        val bundle = arguments
        imType = bundle!!.getString(EXTRA_IM_TYPE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<WhatsAppVoipBinding>(
            inflater, R.layout.fragment_whats_app_voip, container, false
        ).apply {
            viewModel = whatsAppVoipViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun initViews(view: View) {
        adContainerView = view.findViewById(R.id.ad_view_container)
        adView = AdView(mContext)
        adView.adUnitId = Constants.bannerAdId
        adContainerView.addView(adView)

        mAdapter = WhatsAppVoipAdapter(mContext, whatsAppVoipViewModel)
        howItWorks.movementMethod = LinkMovementMethod.getInstance()
        recycler_view.adapter = mAdapter
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        setCallsObserver()
        setFilteredCallsObserver()
        setSelectedChatObserver()
        setOnDataRefreshListener()
        setOnRefreshListener()

        if (Util.showLoadAds(mContext)) {
            loadBannerAd()
            AdMobUtil.loadInterstitialAds(mContext)
        }
    }

    private fun setOnRefreshListener() {
        swipe_refresh_layout.setOnRefreshListener {
            loadVoipCalls()
        }
    }

    private fun loadVoipCalls() {
        whatsAppVoipViewModel.loadVoipCalls()
    }

    private fun stopRefreshing() {
        if (swipe_refresh_layout.isRefreshing)
            swipe_refresh_layout.isRefreshing = false
    }

    private fun setOnDataRefreshListener() {
        refresh_data.setOnClickListener {
            loadVoipCalls()
        }
    }

    private fun setCallsObserver() {
        whatsAppVoipViewModel.voipCallList.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    if (it.isNullOrEmpty()) {
                        hideDataSection()
                    } else {
                        showDataSection()
                        onCallsListUpdated(it)
                    }
                }
            })
    }

    private fun onCallsListUpdated(calls: List<VoipCall>) {
        whatsAppVoipViewModel.setOriginalCalls(calls)
        whatsAppVoipViewModel.setFilteredCalls(calls)
    }

    private fun setFilteredCallsObserver() {
        whatsAppVoipViewModel.getFilteredVoipCalls().observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.submitList(it)
            }
            stopRefreshing()
        })
    }

    private fun setSelectedChatObserver() {
        whatsAppVoipViewModel.getSelectedVoipCall().observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    fun onSearchQuery(query: String) {
        val compositeDisposable = CompositeDisposable()
        val disposable = whatsAppVoipViewModel.performSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }.subscribe(
                {
                    whatsAppVoipViewModel.setFilteredCalls(it)
                }, {
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun showDataSection() {
        whatsAppVoipViewModel.setLoadDataSection(true)
    }

    private fun hideDataSection() {
        whatsAppVoipViewModel.setLoadDataSection(false)
    }

    companion object {
        val TAG = WhatsAppVoipFragment::class.java.simpleName
        private const val EXTRA_IM_TYPE = "EXTRA_IM_TYPE"

        fun newInstance(imType: IMType): WhatsAppVoipFragment {
            val args = Bundle()
            val fragment = WhatsAppVoipFragment()
            args.putString(EXTRA_IM_TYPE, imType.toString())
            fragment.arguments = args
            return fragment
        }
    }
}

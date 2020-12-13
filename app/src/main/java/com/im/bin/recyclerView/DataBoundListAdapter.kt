package com.im.bin.recyclerView

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.im.bin.appUtils.FontManager
import com.im.bin.databinding.ItemChatBinding
import com.im.bin.databinding.ItemWhatsAppVideo
import com.im.bin.databinding.ItemWhatsAppVoiceNote
import com.im.bin.databinding.ItemWhatsAppVoip

abstract class DataBoundListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBoundViewHolder>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val viewDataBinding = createBinding(parent, viewType)
        if (viewDataBinding is ItemChatBinding) {
            FontManager.markAsIconContainer(
                viewDataBinding.chatLayout,
                FontManager.getTypeface(parent.context, FontManager.FONT_AWESOME)
            )
        } else if (viewDataBinding is ItemWhatsAppVoip) {
            FontManager.markAsIconContainer(
                viewDataBinding.voipLayout,
                FontManager.getTypeface(parent.context, FontManager.FONT_AWESOME)
            )
        } else if (viewDataBinding is ItemWhatsAppVideo) {
            FontManager.markAsIconContainer(
                viewDataBinding.videoLayout,
                FontManager.getTypeface(parent.context, FontManager.FONT_AWESOME)
            )
        } else if (viewDataBinding is ItemWhatsAppVoiceNote) {
            FontManager.markAsIconContainer(
                viewDataBinding.voiceLayout,
                FontManager.getTypeface(parent.context, FontManager.FONT_AWESOME)
            )
        }
        return DataBoundViewHolder(viewDataBinding)
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        if (position < itemCount) {
            bind(holder, holder.binding, getItem(position))
            holder.binding.executePendingBindings()
        }
    }

    override fun onViewAttachedToWindow(holder: DataBoundViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: DataBoundViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

    protected abstract fun bind(holder: DataBoundViewHolder, binding: ViewDataBinding, item: T)
}
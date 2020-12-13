package com.im.bin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.im.bin.R
import com.im.bin.databinding.ItemChatBinding
import com.im.bin.models.Chat
import com.im.bin.recyclerView.DataBoundListAdapter
import com.im.bin.recyclerView.DataBoundViewHolder
import com.im.bin.viewModel.WhatsAppChatViewModel
import java.util.*

class WhatsAppChatAdapter(
    private val viewModel: WhatsAppChatViewModel
) : DataBoundListAdapter<Chat>(
    diffCallback = object : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_chat,
            parent,
            false
        )
    }

    override fun bind(holder: DataBoundViewHolder, binding: ViewDataBinding, item: Chat) {
        when (binding) {
            is ItemChatBinding -> {
                binding.chat = item
                binding.viewModel = viewModel
            }
        }
    }
}
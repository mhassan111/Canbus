package com.im.bin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.im.bin.R
import com.im.bin.databinding.ItemIncomingMsgBinding
import com.im.bin.databinding.ItemOutgoingMsgBinding
import com.im.bin.models.IMMessage
import com.im.bin.recyclerView.DataBoundListAdapter
import com.im.bin.recyclerView.DataBoundViewHolder
import com.im.bin.recyclerView.ItemTouchCallbacListener
import com.im.bin.viewModel.ChatConversationViewModel
import java.util.*

class ChatConversationAdapter(
    private val viewModel: ChatConversationViewModel
) : DataBoundListAdapter<IMMessage>(

    diffCallback = object : DiffUtil.ItemCallback<IMMessage>() {
        override fun areItemsTheSame(oldItem: IMMessage, newItem: IMMessage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IMMessage, newItem: IMMessage): Boolean {
            return oldItem == newItem
        }
    }
), ItemTouchCallbacListener.ActionCompletionContract {

    private lateinit var touchHelper: ItemTouchHelper

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        val messages = viewModel.getFilteredConversation().value!!
        return if (messages.isNotEmpty()) {
            val currentMessage = messages[position]
            when (currentMessage.type!!.toLowerCase(Locale.ROOT)) {
                "outgoing" -> VIEW_TYPE_MESSAGE_SENT
                "incoming" -> VIEW_TYPE_MESSAGE_RECEIVED
                else -> VIEW_TYPE_MESSAGE_RECEIVED
            }
        } else {
            super.getItemViewType(position)
        }
    }

    fun setTouchHelper(touchHelper: ItemTouchHelper) {
        this.touchHelper = touchHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val viewDataBinding = createBinding(parent, viewType)
        return DataBoundViewHolder(viewDataBinding)
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        when (viewType) {
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_incoming_msg,
                    parent,
                    false
                )
            }
            else -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_outgoing_msg,
                    parent,
                    false
                )
            }
        }
    }

    override fun bind(holder: DataBoundViewHolder, binding: ViewDataBinding, item: IMMessage) {
        when (binding) {
            is ItemIncomingMsgBinding -> {
                binding.imMessage = item
                binding.viewModel = viewModel
            }
            is ItemOutgoingMsgBinding -> {
                binding.imMessage = item
                binding.viewModel = viewModel
            }
        }
//        holder.itemView.setOnLongClickListener(OnLongClickListener {
//            touchHelper.startDrag(holder)
//            false
//        })
    }

    override fun onRowSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewModel.deleteMessage(position)
    }

    override fun onRowMoved(
        viewHolder: RecyclerView.ViewHolder,
        fromPosition: Int,
        toPosition: Int
    ) {

    }

    override fun onRowSelected(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onRowClear(viewHolder: RecyclerView.ViewHolder) {

    }
}
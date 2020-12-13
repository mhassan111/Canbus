package com.im.bin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.im.bin.R
import com.im.bin.databinding.ItemWhatsAppVoip
import com.im.bin.db.entities.VoipCall
import com.im.bin.dialog.AudioPlayerDialog
import com.im.bin.recyclerView.DataBoundListAdapter
import com.im.bin.recyclerView.DataBoundViewHolder
import com.im.bin.viewModel.WhatsAppVoipViewModel
import kotlinx.android.synthetic.main.item_voip.view.*
import timber.log.Timber
import java.io.File


class WhatsAppVoipAdapter(
    private val mContext: Context,
    private val viewModel: WhatsAppVoipViewModel
) : DataBoundListAdapter<VoipCall>(
    diffCallback = object : DiffUtil.ItemCallback<VoipCall>() {
        override fun areItemsTheSame(oldItem: VoipCall, newItem: VoipCall): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VoipCall, newItem: VoipCall): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_voip,
            parent,
            false
        )
    }

    override fun bind(holder: DataBoundViewHolder, binding: ViewDataBinding, item: VoipCall) {

        when (binding) {
            is ItemWhatsAppVoip -> {
                binding.voip = item
                binding.viewModel = viewModel
            }
        }

        holder.itemView.option_menu.setOnClickListener { view ->
            showOptionsMenu(holder.adapterPosition, view)
        }

        holder.itemView.voipLayout.setOnClickListener {
            val voipCall = viewModel.getFilteredVoipCalls().value!![holder.adapterPosition]
            if (voipCall.file.isNotEmpty()) {
                playVoipCall(voipCall)
            } else {
                Toast.makeText(
                    mContext,
                    "Voip Call Recording Only Supports Device OS 10 and Greater",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showOptionsMenu(position: Int, view: View) {
        val popup = PopupMenu(mContext, view)
        popup.inflate(R.menu.option_menu)
        val item = popup.menu.findItem(R.id.delete)
        popup.setOnMenuItemClickListener { item ->
            val voipCall = viewModel.getFilteredVoipCalls().value!![position]
            when (item.itemId) {
                R.id.play -> {
                    if (voipCall.file.isNotEmpty()) {
                        playVoipCall(voipCall)
                    } else {
                        Toast.makeText(
                            mContext,
                            "Voip Call Recording Only Supports Device OS 10 and Greater",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                R.id.delete -> {
                    viewModel.delete(voipCall)
                    if (voipCall.file.isNotEmpty()) {
                        val filePath = voipCall.file
                        val isDeleted = File(filePath).delete()
                        if (isDeleted) {
                            MediaScannerConnection.scanFile(
                                mContext,
                                arrayOf(voipCall.file),
                                null
                            ) { path, uri ->
                                Timber.d("File Deleted: $path and Uri: $uri")
                            }
                        }
                    }
                }
                R.id.share -> {
                    if (voipCall.file.isNotEmpty()) {
                        shareFile(voipCall.file)
                    } else {
                        Toast.makeText(
                            mContext,
                            "Voip Call Recording Only Supports Device OS 10 and Greater",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            false
        }
        popup.show()
    }

    private fun shareFile(filePath: String) {
        val file = File(filePath)
        val uri = FileProvider.getUriForFile(mContext, mContext.packageName + ".provider", file)
        val intent = ShareCompat.IntentBuilder.from(mContext as Activity)
            .setType("audio/*")
            .setStream(uri)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        mContext.startActivity(intent)
    }

    private fun playVoipCall(voipCall: VoipCall) {
        val audioPlayerDialog = AudioPlayerDialog(mContext, voipCall.voipName, voipCall.file)
        audioPlayerDialog.setCancelable(false)
        audioPlayerDialog.setCanceledOnTouchOutside(false)
        audioPlayerDialog.show()
    }
}
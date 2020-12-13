package com.im.bin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.im.bin.R
import com.im.bin.appUtils.Util
import com.im.bin.databinding.ItemWhatsAppPhoto
import com.im.bin.databinding.ItemWhatsAppVideo
import com.im.bin.databinding.ItemWhatsAppVoiceNote
import com.im.bin.db.entities.WhatsAppMedia
import com.im.bin.enums.WhatsAppMediaType
import com.im.bin.recyclerView.DataBoundListAdapter
import com.im.bin.recyclerView.DataBoundViewHolder
import com.im.bin.viewModel.WhatsAppPhotosViewModel
import kotlinx.android.synthetic.main.item_photo.view.*
import org.apache.commons.io.FilenameUtils
import java.io.File

class WhatsAppMediaAdapter(
    private val mContext: Context,
    private val viewModel: WhatsAppPhotosViewModel
) : DataBoundListAdapter<WhatsAppMedia>(
    diffCallback = object : DiffUtil.ItemCallback<WhatsAppMedia>() {
        override fun areItemsTheSame(oldItem: WhatsAppMedia, newItem: WhatsAppMedia): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WhatsAppMedia, newItem: WhatsAppMedia): Boolean {
            return oldItem == newItem
        }
    }
) {

    companion object {
        private const val VIEW_TYPE_WHATS_APP_PHOTOS = 1
        private const val VIEW_TYPE_WHATS_APP_VIDEOS = 2
        private const val VIEW_TYPE_WHATS_APP_VOICE_NOTES = 3
    }

    override fun getItemViewType(position: Int): Int {
        val mediaList = viewModel.getMediaList().value!!
        return (if (mediaList.isNotEmpty()) {
            val currentMedia = mediaList[position]
            when (currentMedia.mediaType) {
                WhatsAppMediaType.WHATS_APP_PHOTOS.toString() -> VIEW_TYPE_WHATS_APP_PHOTOS
                WhatsAppMediaType.WHATS_APP_VIDEOS.toString() -> VIEW_TYPE_WHATS_APP_VIDEOS
                WhatsAppMediaType.WHATS_APP_VOICE_NOTES.toString() -> VIEW_TYPE_WHATS_APP_VOICE_NOTES
                WhatsAppMediaType.WHATS_APP_STATUSES.toString() -> {
                    val extension = FilenameUtils.getExtension(currentMedia.mediaPath)
                    return if (Util.isImageFileExtension(currentMedia.mediaPath)) {
                        VIEW_TYPE_WHATS_APP_PHOTOS
                    } else {
                        VIEW_TYPE_WHATS_APP_VIDEOS
                    }
                }
                else -> VIEW_TYPE_WHATS_APP_PHOTOS
            }
        } else {
            super.getItemViewType(position)
        })
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        when (viewType) {
            VIEW_TYPE_WHATS_APP_PHOTOS -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_photo,
                    parent,
                    false
                )
            }
            VIEW_TYPE_WHATS_APP_VIDEOS -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_video,
                    parent,
                    false
                )
            }
            VIEW_TYPE_WHATS_APP_VOICE_NOTES -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_voice_note,
                    parent,
                    false
                )
            }
            else -> {
                return DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_photo,
                    parent,
                    false
                )
            }
        }
    }

    override fun bind(holder: DataBoundViewHolder, binding: ViewDataBinding, item: WhatsAppMedia) {
        val whatsAppMedia = viewModel.getMediaList().value!![holder.adapterPosition]
        when (binding) {
            is ItemWhatsAppPhoto -> {
                binding.photo = item
                binding.viewModel = viewModel
                Glide.with(mContext)
                    .load(Uri.fromFile(File(whatsAppMedia.mediaPath!!)))
                    .apply(RequestOptions().centerCrop())
                    .error(R.drawable.image_placeholder)
                    .placeholder(R.drawable.image_placeholder)
                    .into(holder.itemView.image)
            }
            is ItemWhatsAppVideo -> {
                binding.video = item
                binding.viewModel = viewModel
                Glide.with(mContext)
                    .asBitmap()
                    .load(Uri.fromFile(File(whatsAppMedia.mediaPath!!)))
                    .apply(RequestOptions().centerCrop())
                    .error(R.drawable.video_placeholder)
                    .placeholder(R.drawable.image_placeholder)
                    .into(holder.itemView.image)
            }
            is ItemWhatsAppVoiceNote -> {
                binding.voiceNote = item
                binding.viewModel = viewModel
            }
        }
    }
}
package uz.nurlibaydev.eventdetector.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.nurlibaydev.eventdetector.data.room.EventEntity
import uz.nurlibaydev.eventdetector.utils.Companion
import uz.unidev.eventdetector.databinding.ItemEventBinding

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 17:11
 */

class EventAdapter : ListAdapter<EventEntity, EventAdapter.EventViewHolder>(itemCallBack) {

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.switchEventStatus.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                switchChange?.invoke(data.copy(status = 1 - data.status))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.tvEventName.text = data.name
            binding.switchEventStatus.isChecked = data.status == 1
            binding.ivEvent.setImageResource(Companion.list[absoluteAdapterPosition])
        }
    }

    private var switchChange: ((EventEntity) -> Unit)? = null
    fun switchStatusChangeListener(block: (EventEntity) -> Unit) {
        switchChange = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        return holder.onBind()
    }
}

private val itemCallBack = object : DiffUtil.ItemCallback<EventEntity>() {
    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.name == newItem.name && oldItem.status == newItem.status
    }

}
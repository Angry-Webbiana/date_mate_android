package com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ItemPrivateSpaceListBinding
import com.angrywebbiana.datemate.domain.model.Schedule

class PrivateScheduleAdapter: ListAdapter<Schedule, PrivateScheduleAdapter.ViewHolder>(PrivateScheduleDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemPrivateSpaceListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Schedule) {
            binding.schedule = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_private_space_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ItemGroupBinding
import com.angrywebbiana.datemate.domain.model.response.GroupList

class GroupAdapter(

): ListAdapter<GroupList, GroupAdapter.ViewHolder>(GroupDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemGroupBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GroupList) {
            binding.groupList = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_group, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
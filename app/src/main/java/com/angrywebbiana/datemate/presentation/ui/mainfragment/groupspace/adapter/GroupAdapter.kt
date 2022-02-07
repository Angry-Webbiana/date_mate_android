package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ItemGroupBinding
import com.angrywebbiana.datemate.domain.model.response.GroupList
import com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.GroupSpaceActivity

class GroupAdapter(
    private val context: Context
): ListAdapter<GroupList, GroupAdapter.ViewHolder>(GroupDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemGroupBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GroupList) {
            binding.groupList = item

            binding.cvAddFollower.setOnClickListener {
                val intent = Intent(context, GroupSpaceActivity::class.java)
                intent.putExtra("groupId", item.groupId)
                intent.putExtra("groupName", item.group.groupName)
                context.startActivity(intent)
            }
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
package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.adapter

import androidx.recyclerview.widget.DiffUtil
import com.angrywebbiana.datemate.domain.model.response.GroupList

class GroupDiffCallBack(): DiffUtil.ItemCallback<GroupList>() {
    override fun areItemsTheSame(oldItem: GroupList, newItem: GroupList): Boolean {
        return oldItem.groupId == newItem.groupId
    }

    override fun areContentsTheSame(oldItem: GroupList, newItem: GroupList): Boolean {
        return oldItem == newItem
    }
}
package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter

import androidx.recyclerview.widget.DiffUtil
import com.angrywebbiana.datemate.domain.model.User
import com.angrywebbiana.datemate.domain.model.response.FollowersList

class FollowersDiffCallBack(): DiffUtil.ItemCallback<FollowersList>() {
    override fun areItemsTheSame(oldItem: FollowersList, newItem: FollowersList): Boolean {
        return oldItem.relationUserSeq == newItem.relationUserSeq
    }

    override fun areContentsTheSame(oldItem: FollowersList, newItem: FollowersList): Boolean {
        return oldItem == newItem
    }
}

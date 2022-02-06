package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter

import androidx.recyclerview.widget.DiffUtil
import com.angrywebbiana.datemate.domain.model.User

class FollowersDiffCallBack(): DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userSeq == newItem.userSeq
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

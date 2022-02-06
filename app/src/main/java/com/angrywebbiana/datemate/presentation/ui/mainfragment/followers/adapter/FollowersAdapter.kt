package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ItemFollowersBinding
import com.angrywebbiana.datemate.domain.model.User

class FollowersAdapter(

): ListAdapter<User, FollowersAdapter.ViewHolder>(FollowersDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemFollowersBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.follower = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_followers, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
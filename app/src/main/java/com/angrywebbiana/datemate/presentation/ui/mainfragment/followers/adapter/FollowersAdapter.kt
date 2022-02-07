package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ItemFollowersBinding
import com.angrywebbiana.datemate.domain.model.response.FollowersList
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.FollowerSpaceActivity

class FollowersAdapter(
    private val context: Context
): ListAdapter<FollowersList, FollowersAdapter.ViewHolder>(FollowersDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemFollowersBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FollowersList) {
            binding.follower = item
            binding.cvAddFollower.setOnClickListener {
                val intent = Intent(context, FollowerSpaceActivity::class.java)
                intent.putExtra("followerName", item.user.userName)
                intent.putExtra("followerId", item.user.userId)
                context.startActivity(intent)
            }
            Log.d("TESTLOG", "follower name: ${item.user.userName} / email: ${item.user.email}")
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
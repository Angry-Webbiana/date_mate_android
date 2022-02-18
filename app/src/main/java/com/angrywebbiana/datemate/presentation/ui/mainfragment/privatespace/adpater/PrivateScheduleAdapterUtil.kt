package com.angrywebbiana.datemate.presentation.ui.mainfragment.privatespace.adpater

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.angrywebbiana.datemate.domain.model.Schedule
import java.text.SimpleDateFormat
import java.util.*

class PrivateScheduleDiffCallBack(): DiffUtil.ItemCallback<Schedule>() {
    override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
        return oldItem.seq == newItem.seq
    }

    override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("bind_date")
fun bindDate(v: TextView, dateTime: Date) {
    val sdf = SimpleDateFormat("yy/MM/dd", Locale.getDefault())
    val date = sdf.format(dateTime)
    v.text = date
}

@BindingAdapter("bind_time")
fun bindTime(v: TextView, dateTime: Date) {
    val sdf = SimpleDateFormat("hh:mm aa", Locale.getDefault())
    val date = sdf.format(dateTime)
    v.text = date
}
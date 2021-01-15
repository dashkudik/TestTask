package com.example.testtask.presentation_layer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testtask.R
import com.example.testtask.data_layer.pojo.User

class UserListAdapter(
        private val onLongClick: (User, Int) -> Unit,
        private val onClick: (User) -> Unit
) :
        ListAdapter<User, UserViewHolder>(object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) = newItem.name == oldItem.name
            override fun areContentsTheSame(oldItem: User, newItem: User) = newItem.name == oldItem.name
        }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            UserViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.user_item, parent, false),
                    onLongClick,
                    onClick
            )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
            holder.bind(getItem(position), position)
}
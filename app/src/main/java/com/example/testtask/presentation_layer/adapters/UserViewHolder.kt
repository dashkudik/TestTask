package com.example.testtask.presentation_layer.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.data_layer.pojo.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder(
        itemView: View,
        private val showDialog: (User, Int) -> Unit,
        private val openFragment: (User) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
    fun bind(user: User, position: Int) {
        with(itemView) {
            tv_full_name.text = "${user.name} ${user.surname}"
            tv_email.text = user.mail
            Picasso
                    .with(context)
                    .load(user.avatar)
                    .into(img_avatar)
            setOnLongClickListener {
                showDialog(user, position)
                true
            }
            setOnClickListener {
                openFragment(user)
            }
        }
    }
}
package com.example.testtask.presentation_layer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtask.R
import com.example.testtask.presentation_layer.main_activity.MainActivity.Companion.KEY_AVATAR
import com.example.testtask.presentation_layer.main_activity.MainActivity.Companion.KEY_FULL_NAME
import com.example.testtask.presentation_layer.main_activity.MainActivity.Companion.KEY_MAIL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_user, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireArguments()) {
            tv_full_name.text = getString(KEY_FULL_NAME)
            tv_email.text = getString(KEY_MAIL)
            Picasso.with(context).load(getString(KEY_AVATAR)).into(img_avatar)
        }
    }
}
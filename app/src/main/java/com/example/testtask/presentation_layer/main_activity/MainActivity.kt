package com.example.testtask.presentation_layer.main_activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testtask.R
import com.example.testtask.data_layer.database.UserDatabaseAccessor
import com.example.testtask.data_layer.pojo.User
import com.example.testtask.data_layer.remote_api_service.ApiServiceAccessor
import com.example.testtask.presentation_layer.UserFragment
import com.example.testtask.presentation_layer.adapters.UserListAdapter
import com.example.testtask.presentation_layer.main_activity.presenters.ButtonShowPresenter
import com.example.testtask.presentation_layer.main_activity.presenters.ButtonUpdatePresenter
import com.example.testtask.presentation_layer.main_activity.presenters.DialogEditPresenter
import com.example.testtask.presentation_layer.main_activity.views.ButtonShow
import com.example.testtask.presentation_layer.main_activity.views.ButtonUpdate
import com.example.testtask.presentation_layer.main_activity.views.DialogEdit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), CoroutineScope, ButtonShow, ButtonUpdate, DialogEdit {
    companion object {
        const val KEY_FULL_NAME = "full name"
        const val KEY_MAIL = "mail"
        const val KEY_AVATAR = "avatar"
    }

    override val coroutineContext = Main
    private val recyclerAdapter =
            UserListAdapter(onClick = ::openFragment, onLongClick = ::showDialog)

    private val buttonShowPresenter = ButtonShowPresenter(this)
    private val buttonUpdatePresenter = ButtonUpdatePresenter(this)
    private val dialogEditPresenter = DialogEditPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserDatabaseAccessor.provide(applicationContext)
        ApiServiceAccessor.provide()
        setContentView(R.layout.activity_main)
        recycler.adapter = recyclerAdapter

        btn_update.setOnClickListener {
            launch {
                buttonUpdatePresenter.update()
            }
        }
        btn_load.setOnClickListener {
            launch {
                buttonShowPresenter.load()
            }
        }
    }

    private fun openFragment(user: User) {
        supportFragmentManager.beginTransaction().replace(
                R.id.main_container,
                UserFragment().apply {
                    arguments = Bundle().apply {
                        putString(KEY_FULL_NAME, "${user.name} ${user.surname}")
                        putString(KEY_MAIL, user.mail)
                        putString(KEY_AVATAR, user.avatar)
                    }
                }, null).addToBackStack(null).commit()
    }

    /**
     * DialogEdit impl
     */
    override fun showDialog(user: User, position: Int) {
        var callback: (() -> Unit)? = null
        AlertDialog
                .Builder(this)
                .setView(R.layout.item_edit_dialog)
                .setTitle(getString(R.string.edit))
                .setCancelable(true)
                .setNeutralButton(getString(R.string.remove)) { _, _ ->
                    launch {
                        dialogEditPresenter.removeUser(user)
                    }
                }
                .setPositiveButton(getString(R.string.apply)) { _, _ ->
                    callback!!.invoke()
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                .create().also { dialog ->
                    callback = {
                        val inputName =
                                dialog.findViewById<EditText>(R.id.edit_name)!!.text.toString()
                        val inputSurname =
                                dialog.findViewById<EditText>(R.id.edit_surname)!!.text.toString()
                        val inputMail =
                                dialog.findViewById<EditText>(R.id.edit_mail)!!.text.toString()
                        launch {
                            dialogEditPresenter.update(
                                    user.apply {
                                        name = inputName
                                        surname = inputSurname
                                        mail = inputMail
                                    },
                                    position
                            )
                        }
                    }
                }
                .show()
    }

    /**
     * Message impl
     */
    override fun showMessage(message: String) =
            Toast.makeText(applicationContext, message, LENGTH_SHORT).show()

    /**
     * RecyclerUsers impl
     */
    override fun showList(users: List<User>) =
            recyclerAdapter.submitList(users)

    override fun clearList() =
            recyclerAdapter.submitList(listOf())

    override fun updatedAt(position: Int) =
            recyclerAdapter.notifyItemChanged(position)
}
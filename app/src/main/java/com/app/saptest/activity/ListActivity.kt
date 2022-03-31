package com.app.saptest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.saptest.R
import com.app.saptest.adapter.UserAdapter
import com.app.saptest.databinding.ActivityListBinding
import com.app.saptest.model.UserModel
import com.app.saptest.utils.OnListItemClickListener

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        if (this.intent.hasExtra("userList")) {
            val userList = this.intent.getParcelableArrayListExtra<UserModel>("userList")
            setupListData(userList = userList)
        }
    }

    private fun setupListData(userList: ArrayList<UserModel>?) {
        adapter = UserAdapter(object : OnListItemClickListener {
            override fun onItemClick(userModel: UserModel) {
                redirectToUserDetailsScreen(userModel = userModel)
            }
        })
        binding.rvUser.adapter = adapter
        userList?.let { adapter?.setUsers(users = it) }
    }

    private fun redirectToUserDetailsScreen(userModel: UserModel) {
        startActivity(
            Intent(
                this,
                UserDetailsActivity::class.java
            ).putExtra("userModel", userModel)
        )
    }
}
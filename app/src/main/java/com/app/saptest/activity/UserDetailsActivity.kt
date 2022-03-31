package com.app.saptest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.saptest.R
import com.app.saptest.databinding.ActivityUserDetailsBinding
import com.app.saptest.model.UserModel
import com.bumptech.glide.Glide

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        if (this.intent.hasExtra("userModel")) {
            val userModel = this.intent.getParcelableExtra<UserModel>("userModel")
            userModel?.let { setUser(userModel = it) }
        }
    }

    private fun setUser(userModel: UserModel) {
        binding.txtName.text = userModel.name ?: ""
        binding.txtDescription.text = userModel.description ?: ""
        if (!userModel.image.isNullOrEmpty()) {
            Glide.with(this).load(userModel.image).into(binding.imgUser)
        }
    }
}
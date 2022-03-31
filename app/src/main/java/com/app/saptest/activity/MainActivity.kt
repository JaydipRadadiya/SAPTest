package com.app.saptest.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.saptest.R
import com.app.saptest.databinding.ActivityMainBinding
import com.app.saptest.model.UserModel
import com.app.saptest.network.RetrofitClient
import com.app.saptest.repository.MainRepository
import com.app.saptest.viewmodel.MainViewModel
import com.app.saptest.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private var userList: ArrayList<UserModel>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val retrofitClient = RetrofitClient.getInstance()
        val mainRepository = MainRepository(retrofitClient)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
        initLiveData()
        getUsersAPI()
        setClickEvents()
    }

    private fun setClickEvents() {
        binding.viewLeftTop.setOnClickListener {
            redirectToListScreen()
        }
        binding.viewRightTop.setOnClickListener {
            redirectToListScreen()
        }
    }

    private fun redirectToListScreen() {
        if (userList.isNullOrEmpty()) return
        startActivity(
            Intent(
                this,
                ListActivity::class.java
            ).putParcelableArrayListExtra("userList", userList)
        )
    }

    private fun getUsersAPI() {
        viewModel.getUsers()
    }

    private fun initLiveData() {
        viewModel.userList.observe(this) {
            Log.e("MainActivity", "### userList: ${it.size}")
            userList?.addAll(it)
        }

        viewModel.errorMessage.observe(this) {
            Log.e("MainActivity", "initLiveData: $it")
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
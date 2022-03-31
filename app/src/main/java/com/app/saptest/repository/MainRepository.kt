package com.app.saptest.repository

import com.app.saptest.network.RetrofitClient

class MainRepository constructor(private val retrofitService: RetrofitClient) {
    suspend fun getUsers() = retrofitService.getUsers()
}
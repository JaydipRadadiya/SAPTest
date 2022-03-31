package com.app.saptest.network

import com.app.saptest.model.UserModel
import com.app.saptest.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {

    @GET(Constant.API_VERSION + "/99066355-8f5e-4c9d-b400-d5bdf26911b6")
    suspend fun getUsers() : Response<List<UserModel>>

    companion object {
        var retrofitClient: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            if (retrofitClient == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitClient = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitClient!!
        }
    }
}
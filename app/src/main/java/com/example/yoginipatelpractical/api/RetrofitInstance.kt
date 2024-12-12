package com.example.yoginipatelpractical.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : UserInterface by lazy {
        Retrofit.Builder()
//        https://api.stackexchange.com/2.3/users?page=1&pagesize=10&site=stackoverflow&sort=name
            .baseUrl("https://api.stackexchange.com/2.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserInterface::class.java)
    }
}
package com.example.yoginipatelpractical.api

import com.example.yoginipatelpractical.models.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInterface {

    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("site") site: String = "stackoverflow",
        @Query("sort") sort: String
    ): Call<UserResponseModel>
}
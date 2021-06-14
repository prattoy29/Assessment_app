package com.example.assessmentapp.api

import com.example.assessmentapp.model.Userinfo
import retrofit2.Response
import retrofit2.http.GET

interface Retrofit {
    @GET("posts")
    suspend fun getCustomPost(): Response<List<Userinfo>>
}
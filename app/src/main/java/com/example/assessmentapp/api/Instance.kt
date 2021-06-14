package com.example.assessmentapp.api
import com.example.assessmentapp.util.Constants.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
object Instance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: com.example.assessmentapp.api.Retrofit by lazy {
        retrofit.create(com.example.assessmentapp.api.Retrofit::class.java)
    }
}
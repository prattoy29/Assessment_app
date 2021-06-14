package com.example.assessmentapp.repository

import com.example.assessmentapp.api.Instance
import com.example.assessmentapp.model.Userinfo
import retrofit2.Response

class Repository {

    suspend fun getCustomPost(): Response<List<Userinfo>> {
        return Instance.api.getCustomPost()
    }

}
package com.example.assessmentapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentapp.model.Userinfo
import com.example.assessmentapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FragmentViewModel(private val repository: Repository) : ViewModel() {
    var myCustomPost: MutableLiveData<Response<List<Userinfo>>> = MutableLiveData()

    fun getCustomPosts() {
        viewModelScope.launch {
            val response = repository.getCustomPost()
            myCustomPost.value = response
        }
    }


}
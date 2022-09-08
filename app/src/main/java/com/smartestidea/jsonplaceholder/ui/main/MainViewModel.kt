package com.smartestidea.jsonplaceholder.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartestidea.jsonplaceholder.data.model.Post
import com.smartestidea.jsonplaceholder.domain.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
):ViewModel() {
    val posts = MutableLiveData<List<Post>>()
    val loading = MutableLiveData<Boolean>()


    fun onCreate(isConnect:Boolean){
        viewModelScope.launch{
            loading.postValue(true)
            val response = getPostsUseCase(isConnect)
            if (!response.isNullOrEmpty())
                posts.postValue(response)

            loading.postValue(false)
        }
    }
}
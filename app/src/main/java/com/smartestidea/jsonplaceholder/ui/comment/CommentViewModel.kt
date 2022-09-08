package com.smartestidea.jsonplaceholder.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartestidea.jsonplaceholder.data.model.Comment
import com.smartestidea.jsonplaceholder.domain.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase
):ViewModel() {

    val comments = MutableLiveData<List<Comment>>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(postId:Int,isConnect:Boolean){
        viewModelScope.launch{
            loading.postValue(true)
            val response = getCommentsUseCase(postId,isConnect)
            if (!response.isNullOrEmpty())
                comments.postValue(response)

            loading.postValue(false)
        }
    }
}
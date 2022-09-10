package com.smartestidea.jsonplaceholder.data.network

import com.smartestidea.jsonplaceholder.data.model.Comment
import com.smartestidea.jsonplaceholder.data.network.PostApiClient
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.CommentNetwork
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.PostNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostService @Inject constructor(
    private val apiClient: PostApiClient
){
    suspend fun getPosts(): List<PostNetwork> = withContext(Dispatchers.IO) {
            apiClient.getPosts()?.body() ?: emptyList()
        }

    suspend fun getComments(postId: Int): List<CommentNetwork> =
        withContext(Dispatchers.IO){
        apiClient.getComments(postId).body() ?: emptyList()
    }

}

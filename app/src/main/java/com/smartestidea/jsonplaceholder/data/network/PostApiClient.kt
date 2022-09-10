package com.smartestidea.jsonplaceholder.data.network

import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.CommentNetwork
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.PostNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PostApiClient {
    @GET("posts")
    suspend fun getPosts():Response<List<PostNetwork>>?

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId:Int
    ):Response<List<CommentNetwork>>
}
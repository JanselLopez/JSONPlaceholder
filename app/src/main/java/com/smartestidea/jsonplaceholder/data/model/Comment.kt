package com.smartestidea.jsonplaceholder.data.model

import com.smartestidea.jsonplaceholder.data.database.entities.CommentEntity
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.CommentNetwork

data class Comment(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body:String
)
fun CommentNetwork.toDomain() = Comment(postId, id, name, email, body)
fun CommentEntity.toDomain() = Comment(postId, id, name, email, body)
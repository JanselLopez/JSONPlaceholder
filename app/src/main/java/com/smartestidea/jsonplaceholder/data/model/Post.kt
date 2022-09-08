package com.smartestidea.jsonplaceholder.data.model

import com.smartestidea.jsonplaceholder.data.database.entities.PostEntity
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.PostNetwork

data class Post(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
)

fun PostNetwork.toDomain() = Post(userId, id, title, body)
fun PostEntity.toDomain() = Post(userId, id, title, body)

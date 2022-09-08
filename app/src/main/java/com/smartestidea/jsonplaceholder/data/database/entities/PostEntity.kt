package com.smartestidea.jsonplaceholder.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartestidea.jsonplaceholder.data.model.Post
import com.smartestidea.jsonplaceholder.data.network.modelsnetwork.PostNetwork

@Entity(tableName = "posts_table")
data class PostEntity (
    @ColumnInfo(name="userId")val userId:Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="title")val title:String,
    @ColumnInfo(name="body")val body:String
    )

fun Post.toDomain() = PostEntity(userId, id, title, body)
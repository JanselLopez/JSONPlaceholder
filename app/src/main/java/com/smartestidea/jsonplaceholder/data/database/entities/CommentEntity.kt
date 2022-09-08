package com.smartestidea.jsonplaceholder.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartestidea.jsonplaceholder.data.model.Comment
import com.smartestidea.jsonplaceholder.data.model.Post

@Entity(tableName = "comments_table")
data class CommentEntity(
    @ColumnInfo(name="postId")val postId:Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")val id:Int,
    @ColumnInfo(name="name")val name:String,
    @ColumnInfo(name="email")val email:String,
    @ColumnInfo(name="body")val body:String
)

fun Comment.toDomain() = CommentEntity(postId, id, name, email, body)

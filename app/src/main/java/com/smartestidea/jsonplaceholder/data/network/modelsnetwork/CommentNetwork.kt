package com.smartestidea.jsonplaceholder.data.network.modelsnetwork

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comments_table")
data class CommentNetwork(
    @SerializedName("postId")val postId:Int,
    @SerializedName("id")val id:Int,
    @SerializedName("name")val name:String,
    @SerializedName("email")val email:String,
    @SerializedName("body")val body:String
)



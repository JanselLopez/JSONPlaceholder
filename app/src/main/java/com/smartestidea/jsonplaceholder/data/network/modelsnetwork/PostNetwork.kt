package com.smartestidea.jsonplaceholder.data.network.modelsnetwork

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts_table")
data class PostNetwork (
    @SerializedName("userId")val userId:Int,
    @SerializedName("id")val id:Int,
    @SerializedName("title")val title:String,
    @SerializedName("body")val body:String
    )
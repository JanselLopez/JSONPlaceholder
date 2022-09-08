package com.smartestidea.jsonplaceholder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartestidea.jsonplaceholder.data.database.dao.PostDao
import com.smartestidea.jsonplaceholder.data.database.entities.PostEntity
import com.smartestidea.jsonplaceholder.data.database.entities.CommentEntity

@Database(entities = [CommentEntity::class, PostEntity::class],version = 1)
abstract class PostDatabase:RoomDatabase() {

    abstract fun getPostDao(): PostDao
}
package com.smartestidea.jsonplaceholder.data.database.dao

import androidx.room.*
import com.smartestidea.jsonplaceholder.data.database.entities.PostEntity
import com.smartestidea.jsonplaceholder.data.database.entities.CommentEntity
import com.smartestidea.jsonplaceholder.data.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts_table")
    suspend fun getPosts():List<PostEntity>?

    @Query("SELECT * FROM comments_table WHERE postId = :postId")
    suspend fun getComments(postId:Int): List<CommentEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments:List<CommentEntity>)

    @Query("DELETE FROM posts_table")
    suspend fun clearPosts()

    @Query("DELETE FROM comments_table")
    suspend fun clearComments()

}
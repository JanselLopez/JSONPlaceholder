package com.smartestidea.jsonplaceholder.data

import com.smartestidea.jsonplaceholder.data.database.dao.PostDao
import com.smartestidea.jsonplaceholder.data.database.entities.PostEntity
import com.smartestidea.jsonplaceholder.data.database.entities.CommentEntity
import com.smartestidea.jsonplaceholder.data.network.PostService
import com.smartestidea.jsonplaceholder.data.model.Comment
import com.smartestidea.jsonplaceholder.data.model.Post
import com.smartestidea.jsonplaceholder.data.model.toDomain
import com.smartestidea.jsonplaceholder.core.provider.ContactProvider
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postService: PostService,
    private val postDao: PostDao,
) {
    suspend fun getPostsFromApi(): List<Post> = postService.getPosts().map { it.toDomain() }

    suspend fun getCommentsFromApi(postId: Int): List<Comment> = postService.getComments(postId).map { it.toDomain() }

    suspend fun getPostsFromDatabase(): List<Post> =  postDao.getPosts()!!.map { it.toDomain() }

    suspend fun getCommentsFromDatabase(postId: Int): List<Comment> = postDao.getComments(postId)!!.map { it.toDomain() }

    suspend fun insertPosts(posts: List<PostEntity>) = postDao.insertPosts(posts)

    suspend fun insertComments(comments: List<CommentEntity>) = postDao.insertComments(comments)

    suspend fun clearPosts() = postDao.clearPosts()

    suspend fun clearComments() = postDao.clearComments()

}
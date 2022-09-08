package com.smartestidea.jsonplaceholder.domain

import com.smartestidea.jsonplaceholder.data.PostsRepository
import com.smartestidea.jsonplaceholder.data.database.entities.toDomain
import com.smartestidea.jsonplaceholder.data.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(isConnect:Boolean): List<Post> = if (isConnect) {
            val posts = repository.getPostsFromApi()
            repository.clearPosts()
            repository.insertPosts(posts.map { it.toDomain() })
            posts}
        else
            repository.getPostsFromDatabase()
}
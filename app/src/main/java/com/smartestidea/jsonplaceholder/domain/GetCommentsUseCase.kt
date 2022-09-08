package com.smartestidea.jsonplaceholder.domain

import com.smartestidea.jsonplaceholder.data.PostsRepository
import com.smartestidea.jsonplaceholder.data.database.entities.toDomain
import com.smartestidea.jsonplaceholder.data.model.Comment
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(postId:Int,isConnect:Boolean): List<Comment> = if (isConnect) {
            val comments = repository.getCommentsFromApi(postId)
            repository.clearComments()
            repository.insertComments(comments.map { it.toDomain() })
            comments
        }else
            repository.getCommentsFromDatabase(postId)

}
package com.smartestidea.jsonplaceholder.domain

import android.content.Context
import com.smartestidea.jsonplaceholder.data.PostsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(context: Context) = repository.getContacts(context)
}
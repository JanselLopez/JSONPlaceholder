package com.smartestidea.jsonplaceholder.core.di

import android.content.Context
import androidx.room.Room
import com.smartestidea.jsonplaceholder.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java,"posts_database").build()

    @Singleton
    @Provides
    fun providePostDao(db: PostDatabase) = db.getPostDao()
}
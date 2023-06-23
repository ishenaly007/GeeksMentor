package com.abit8.geeksmentor.di

import android.content.Context
import androidx.room.Room
import com.abit8.geeksmentor.data.repository.MentorRepositoryImpl
import com.abit8.geeksmentor.domain.repository.MentorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MentorAppModule {

    @Provides
    fun provideMentorRepository(): MentorRepository = MentorRepositoryImpl()
}
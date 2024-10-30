package com.openparty.app.di

import com.openparty.app.feature_newsfeed.data.datasource.NewsfeedDataSource
import com.openparty.app.feature_newsfeed.data.repository.NewsfeedRepositoryImpl
import com.openparty.app.feature_newsfeed.domain.repository.NewsfeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsfeedModule {

    @Provides
    @Singleton
    fun provideNewsfeedRepository(
        dataSource: NewsfeedDataSource
    ): NewsfeedRepository = NewsfeedRepositoryImpl(dataSource)
}

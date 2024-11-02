package com.openparty.app.feature_newsfeed.data.repository

import com.openparty.app.feature_newsfeed.data.datasource.NewsfeedDataSource
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem
import com.openparty.app.feature_newsfeed.domain.repository.NewsfeedRepository
import javax.inject.Inject

class NewsfeedRepositoryImpl @Inject constructor(
    private val dataSource: NewsfeedDataSource
) : NewsfeedRepository {
    override suspend fun getNewsfeed(): List<NewsfeedItem> {
        return dataSource.fetchNewsfeed()
    }
}

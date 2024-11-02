package com.openparty.app.feature_newsfeed.domain.repository

import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem

interface NewsfeedRepository {
    suspend fun getNewsfeed(): List<NewsfeedItem>
}

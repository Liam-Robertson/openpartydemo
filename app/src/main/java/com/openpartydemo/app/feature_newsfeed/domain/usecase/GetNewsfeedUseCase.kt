package com.openparty.app.feature_newsfeed.domain.usecase

import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem
import com.openparty.app.feature_newsfeed.domain.repository.NewsfeedRepository
import javax.inject.Inject

class GetNewsfeedUseCase @Inject constructor(
    private val repository: NewsfeedRepository
) {
    suspend operator fun invoke(): List<NewsfeedItem> {
        return repository.getNewsfeed()
    }
}

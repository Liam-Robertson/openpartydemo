package com.openparty.app.feature_newsfeed.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsfeedResponse(
    val newsfeed: List<NewsfeedItem>
)

package com.openparty.app.feature_newsfeed.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsfeedItem(
    val id: Int,
    val title: String,
    val subheader: String,
    val image: String,
    val content: String,
    val timestamp: String
)

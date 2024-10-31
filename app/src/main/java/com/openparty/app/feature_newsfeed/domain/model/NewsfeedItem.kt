package com.openparty.app.feature_newsfeed.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsfeedItem(
    val id: Int,
    val title: String,
    val subheader: String,
    val image: String,
    val content: Content,
    val timestamp: String
)

@Serializable
data class Content(
    val paragraphs: List<String>
)
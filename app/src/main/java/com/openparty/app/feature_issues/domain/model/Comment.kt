package com.openparty.app.feature_issues.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val id: Int,
    val text: String,
    val author: String,
    val upvotes: Int
)
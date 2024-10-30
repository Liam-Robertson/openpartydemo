package com.openparty.app.feature_issues.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String,
    val upvotes: Int,
    val comments: List<Comment> = emptyList()
)
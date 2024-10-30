// File: feature_issues/src/main/java/com/openparty/app/feature_issues/domain/model/Issue.kt
package com.openparty.app.feature_issues.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    val id: Int,
    val title: String,
    val content: String,
    val user: String,
    val upvotes: Int,
    val comments: List<Comment> = emptyList()
)

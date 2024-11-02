package com.openparty.app.feature_issues.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Issue(
    val id: Int,
    val title: String,
    val content: String,
    val user: String,
    val upvotes: Int,
    val comments: List<Comment> = emptyList()
) : Parcelable

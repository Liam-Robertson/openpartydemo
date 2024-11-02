package com.openparty.app.feature_issues.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Comment(
    val id: Int,
    val text: String,
    val author: String,
    val upvotes: Int
) : Parcelable

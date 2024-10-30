// File: feature_issues/src/main/java/com/openparty/feature_issues/data/datasource/IssueDataSource.kt
package com.openparty.app.feature_issues.data.datasource

import android.content.Context
import com.openparty.app.feature_issues.domain.model.Issue
import com.openparty.app.feature_issues.domain.model.IssueResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class IssueDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    suspend fun fetchIssues(): List<Issue> {
        val jsonString = withContext(Dispatchers.IO) {
            context.assets.open("issueScreen/issues.json").bufferedReader().use { it.readText() }
        }
        val response = jsonFormat.decodeFromString<IssueResponse>(jsonString)
        return response.issues
    }
}

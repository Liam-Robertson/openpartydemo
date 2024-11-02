package com.openparty.app.feature_newsfeed.data.datasource

import android.content.Context
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class NewsfeedDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    suspend fun fetchNewsfeed(): List<NewsfeedItem> {
        val jsonString = withContext(Dispatchers.IO) {
            context.assets.open("feature_newsfeed/newsfeedData.json").bufferedReader().use { it.readText() }
        }
        val response = jsonFormat.decodeFromString<NewsfeedResponse>(jsonString)
        return response.newsfeed
    }
}

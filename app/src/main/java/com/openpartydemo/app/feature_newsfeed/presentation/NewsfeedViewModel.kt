package com.openparty.app.feature_newsfeed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openparty.app.feature_newsfeed.domain.model.NewsfeedItem
import com.openparty.app.feature_newsfeed.domain.usecase.GetNewsfeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsfeedViewModel @Inject constructor(
    private val getNewsfeedUseCase: GetNewsfeedUseCase
) : ViewModel() {

    private val _newsfeed = MutableStateFlow<List<NewsfeedItem>>(emptyList())
    val newsfeed: StateFlow<List<NewsfeedItem>> = _newsfeed.asStateFlow()

    init {
        viewModelScope.launch {
            val data = getNewsfeedUseCase()
            _newsfeed.value = data.sortedByDescending { it.timestamp }
        }
    }
}

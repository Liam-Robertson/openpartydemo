// File: feature_issues/src/main/java/com/openparty/feature_issues/presentation/IssuesViewModel.kt
package com.openparty.app.feature_issues.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openparty.app.feature_issues.domain.model.Issue
import com.openparty.app.feature_issues.domain.usecase.GetIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase
) : ViewModel() {

    private val _issues = MutableStateFlow<List<Issue>>(emptyList())
    val issues: StateFlow<List<Issue>> = _issues.asStateFlow()

    init {
        viewModelScope.launch {
            val data = getIssuesUseCase()
            _issues.value = data.sortedByDescending { it.upvotes }
        }
    }
}

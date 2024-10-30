// File: feature_budget/src/main/java/com/openparty/feature_budget/presentation/BudgetViewModel.kt
package com.openparty.app.feature_budget.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.domain.usecase.GetBudgetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getBudgetDataUseCase: GetBudgetDataUseCase
) : ViewModel() {

    private val _budgetData = MutableStateFlow<List<BudgetItem>>(emptyList())
    val budgetData: StateFlow<List<BudgetItem>> = _budgetData.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val data = getBudgetDataUseCase()
            _budgetData.value = data
            val elapsedTime = System.currentTimeMillis() - startTime
            val remainingTime = 1000L - elapsedTime
            if (remainingTime > 0) {
                delay(remainingTime)
            }
            _isLoading.value = false
        }
    }
}

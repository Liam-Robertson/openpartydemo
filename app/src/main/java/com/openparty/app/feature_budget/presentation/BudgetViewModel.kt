// File: feature-budget/src/main/java/com/openparty/feature_budget/presentation/BudgetViewModel.kt
package com.openparty.app.feature_budget.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openparty.app.feature_budget.domain.model.BudgetItem
import com.openparty.app.feature_budget.domain.usecase.GetBudgetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getBudgetDataUseCase: GetBudgetDataUseCase
) : ViewModel() {

    private val _budgetData = MutableStateFlow<List<BudgetItem>>(emptyList())
    val budgetData: StateFlow<List<BudgetItem>> = _budgetData

    init {
        viewModelScope.launch {
            _budgetData.value = getBudgetDataUseCase()
        }
    }
}

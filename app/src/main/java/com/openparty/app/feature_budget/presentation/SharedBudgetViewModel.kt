// File: feature_budget/presentation/SharedBudgetViewModel.kt

package com.openparty.app.feature_budget.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.openparty.app.feature_budget.domain.model.BudgetItem

class SharedBudgetViewModel : ViewModel() {
    private val _selectedItem = MutableStateFlow<BudgetItem?>(null)
    val selectedItem: StateFlow<BudgetItem?> = _selectedItem

    fun setSelectedItem(item: BudgetItem) {
        println("SharedBudgetViewModel - Setting selected item: ${item.typeOfSpending}")
        _selectedItem.value = item
    }
}

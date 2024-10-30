// File: feature_issues/src/main/java/com/openparty/feature_issues/presentation/IssuesScreen.kt
package com.openparty.app.feature_issues.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun IssuesScreen(
    navController: NavController,
    viewModel: IssuesViewModel = hiltViewModel()
) {
    val issues = viewModel.issues.collectAsState().value
    IssuesContent(
        issues = issues,
        onIssueClick = { issue ->
            navController.currentBackStackEntry?.savedStateHandle?.set("issue", issue)
            navController.navigate("view_issue")
        },
        onAddIssueClick = {
            navController.navigate("raise_issue")
        }
    )
}

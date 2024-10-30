// File: feature_issues/src/main/java/com/openparty/feature_issues/presentation/IssuesContent.kt
package com.openparty.app.feature_issues.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openparty.app.feature_issues.domain.model.Issue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssuesContent(
    issues: List<Issue>,
    onIssueClick: (Issue) -> Unit,
    onAddIssueClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddIssueClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Issue")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Issues",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(issues.size) { index ->
                    val issue = issues[index]
                    IssueCard(issue, onIssueClick)
                }
            }
        }
    }
}

@Composable
fun IssueCard(issue: Issue, onIssueClick: (Issue) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onIssueClick(issue) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = issue.title, style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Upvotes")
                Text(text = issue.upvotes.toString())
            }
        }
    }
}

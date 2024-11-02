// File: feature_issues/src/main/java/com/openparty/feature_issues/presentation/IssuesContent.kt
package com.openparty.app.feature_issues.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
            FloatingActionButton(
                onClick = onAddIssueClick,
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Issue",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Issues",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize * 1.2
                ),
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = issue.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize * 1.2
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Upvotes",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = issue.upvotes.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

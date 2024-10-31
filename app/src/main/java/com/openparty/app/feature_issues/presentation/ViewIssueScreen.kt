package com.openparty.app.feature_issues.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.openparty.app.feature_issues.domain.model.Issue

@Composable
fun ViewIssueScreen(
    navController: NavController
) {
    val issue = navController.previousBackStackEntry?.savedStateHandle?.get<Issue>("issue")
    issue?.let {
        IssueDetailContent(issue)
    } ?: run {
        // Handle error or navigate back
    }
}

@Composable
fun IssueDetailContent(issue: Issue) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = issue.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = issue.content,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "By ${issue.user}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Upvotes")
                Text(text = issue.upvotes.toString())
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Comments",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(issue.comments.size) { index ->
                val comment = issue.comments[index]
                CommentCard(comment)
            }
        }
    }
}

@Composable
fun CommentCard(comment: com.openparty.app.feature_issues.domain.model.Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = comment.text)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "By ${comment.author}")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Upvotes")
                    Text(text = comment.upvotes.toString())
                }
            }
        }
    }
}

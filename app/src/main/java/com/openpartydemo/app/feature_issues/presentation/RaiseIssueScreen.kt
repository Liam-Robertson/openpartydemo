// File: feature_issues/src/main/java/com/openparty/feature_issues/presentation/RaiseIssueScreen.kt
package com.openparty.app.feature_issues.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaiseIssueScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Raise An Issue",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Describe your issue") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle submission (for now, we can just navigate back)
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

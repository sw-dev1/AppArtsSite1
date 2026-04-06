package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparts.sites.appartssite1.ui.theme.AppTypography

@Composable
fun ProjectDetailScreen(id: Int, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        TextButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            Text("Back")
        }
        //Text("Project ID: $id", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Project Case Study: ${projects[id].title}  ",
            style = AppTypography.headlineMedium
        )
        Text(text = "${projects[id].description}  ", style = AppTypography.bodyMedium)
    }
}
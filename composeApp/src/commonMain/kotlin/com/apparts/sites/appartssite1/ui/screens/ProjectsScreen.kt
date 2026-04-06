package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apparts.sites.appartssite1.ui.navigation.Project

@Composable
fun ProjectsScreen(onNavigateToProject: (String) -> Unit) {
    val projects = listOf(
        Project("1", "Task Master", "Advanced productivity suite", "KMP"),
        Project("2", "Weather Pulse", "Hyper-local weather", "Android"),
        Project("3", "SecureVault", "Encrypted storage", "Security"),
        Project("4", "EcoTrack", "Sustainability tracker", "Compose")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Curated Work",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(projects) { project ->
                ProjectCard(project, onClick = { onNavigateToProject(project.id) })
            }
        }
    }
}
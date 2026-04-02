package com.apparts.sites.appartssite1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.apparts.sites.appartssite1.ui.theme.AppTheme
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class ProjectDetail(val projectId: String)

@Composable
fun App() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Home) {
                composable<Home> {
                    HomeScreen(onNavigateToProject = { id -> navController.navigate(ProjectDetail(id)) })
                }
                composable<ProjectDetail> { backStackEntry ->
                    val route: ProjectDetail = backStackEntry.toRoute()
                    ProjectDetailScreen(route.projectId, onBack = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigateToProject: (String) -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Hi, welcome to my site", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground)
        Text("Software Developer | Android & Multiplatform Expert", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("Projects", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(16.dp))
        
        ProjectCard(title = "App 1", description = "A cool project", onClick = { onNavigateToProject("1") })
    }
}

@Composable
fun ProjectCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ProjectDetailScreen(id: String, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Button(onClick = onBack) { Text("Back") }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Project Details: $id", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground)
    }
}

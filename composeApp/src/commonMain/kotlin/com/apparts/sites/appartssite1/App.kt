package com.apparts.sites.appartssite1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.apparts.sites.appartssite1.ui.theme.AppTheme
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Projects

@Serializable
object About

@Serializable
data class ProjectDetail(val projectId: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("AppArts Portfolio", fontWeight = FontWeight.Bold) },
                    actions = {
                        TextButton(onClick = { navController.navigate(Home) }) { Text("Home") }
                        TextButton(onClick = { navController.navigate(Projects) }) { Text("Projects") }
                        TextButton(onClick = { navController.navigate(About) }) { Text("About") }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                // Background Gradient
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                )

                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> { HomeScreen(onExploreProjects = { navController.navigate(Projects) }) }
                    composable<Projects> { ProjectsScreen(onNavigateToProject = { id -> navController.navigate(ProjectDetail(id)) }) }
                    composable<About> { AboutScreen() }
                    composable<ProjectDetail> { backStackEntry ->
                        val route: ProjectDetail = backStackEntry.toRoute()
                        ProjectDetailScreen(route.projectId, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onExploreProjects: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f).padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
        ) {
            Column(modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Transforming Ideas into Digital Realities", style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Professional Software Developer.", style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
            }
        }
        
        Button(onClick = onExploreProjects, shape = RoundedCornerShape(16.dp), modifier = Modifier.height(64.dp).fillMaxWidth(0.6f)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
            Spacer(Modifier.width(12.dp))
            Text("View Portfolio", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

data class Project(val id: String, val title: String, val description: String, val category: String)

@Composable
fun ProjectsScreen(onNavigateToProject: (String) -> Unit) {
    val projects = listOf(
        Project("1", "Task Master", "Advanced productivity suite", "KMP"),
        Project("2", "Weather Pulse", "Hyper-local weather", "Android"),
        Project("3", "SecureVault", "Encrypted storage", "Security"),
        Project("4", "EcoTrack", "Sustainability tracker", "Compose")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Curated Work", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 24.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(projects) { project ->
                ProjectCard(project, onClick = { onNavigateToProject(project.id) })
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(project.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(project.category, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            }
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("About the Developer", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Passionate software developer specializing in KMP and Jetpack Compose.", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ProjectDetailScreen(id: String, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        TextButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            Text("Back")
        }
        Text("Project Case Study: $id", style = MaterialTheme.typography.headlineMedium)
    }
}

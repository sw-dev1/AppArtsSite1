package com.apparts.sites.appartssite1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.apparts.sites.appartssite1.ui.theme.AppTheme
import kotlinx.coroutines.launch
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
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Home") },
                        selected = currentRoute?.contains("Home") == true,
                        onClick = {
                            navController.navigate(Home)
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.List, contentDescription = null) },
                        label = { Text("Projects") },
                        selected = currentRoute?.contains("Projects") == true,
                        onClick = {
                            navController.navigate(Projects)
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Info, contentDescription = null) },
                        label = { Text("About") },
                        selected = currentRoute?.contains("About") == true,
                        onClick = {
                            navController.navigate(About)
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("AppArts Portfolio", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            titleContentColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    // Background Image Gradient for a professional feel
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                                        MaterialTheme.colorScheme.background,
                                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.1f)
                                    )
                                )
                            )
                    )

                    NavHost(
                        navController = navController,
                        startDestination = Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Home> {
                            HomeScreen(
                                onExploreProjects = { navController.navigate(Projects) }
                            )
                        }
                        composable<Projects> {
                            ProjectsScreen(
                                onNavigateToProject = { id -> navController.navigate(ProjectDetail(id)) }
                            )
                        }
                        composable<About> {
                            AboutScreen()
                        }
                        composable<ProjectDetail> { backStackEntry ->
                            val route: ProjectDetail = backStackEntry.toRoute()
                            ProjectDetailScreen(route.projectId, onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onExploreProjects: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f).padding(bottom = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Transforming Ideas into Digital Realities",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Professional Software Developer crafting high-performance Android and Multiplatform solutions.",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Button(
            onClick = onExploreProjects,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(64.dp).fillMaxWidth(0.6f),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
            Spacer(Modifier.width(12.dp))
            Text("View Portfolio", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

data class Project(val id: String, val title: String, val description: String, val category: String)

@Composable
fun ProjectsScreen(onNavigateToProject: (String) -> Unit) {
    val projects = listOf(
        Project("1", "Task Master", "Advanced productivity suite with offline support", "Kotlin Multiplatform"),
        Project("2", "Weather Pulse", "Hyper-local weather forecasts using real-time data", "Android Native"),
        Project("3", "SecureVault", "Encrypted storage solution for sensitive information", "Cybersecurity"),
        Project("4", "EcoTrack", "Sustainability tracker for everyday habits", "Jetpack Compose")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Curated Work",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, bottom = 24.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
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
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(project.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(project.category, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    project.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("About the Developer", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "With years of experience in the mobile ecosystem, I specialize in building robust, " +
            "scalable applications using Kotlin and Jetpack Compose. My mission is to bridge the " +
            "gap between complex technology and intuitive user experiences.",
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(modifier = Modifier.height(32.dp))
        Text("Core Competencies", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        FlowRow(
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(selected = true, onClick = {}, label = { Text("Kotlin") })
            FilterChip(selected = true, onClick = {}, label = { Text("Android SDK") })
            FilterChip(selected = true, onClick = {}, label = { Text("Jetpack Compose") })
            FilterChip(selected = true, onClick = {}, label = { Text("KMP") })
            FilterChip(selected = true, onClick = {}, label = { Text("Dagger Hilt") })
            FilterChip(selected = true, onClick = {}, label = { Text("Coroutines") })
        }
    }
}

@Composable
fun ProjectDetailScreen(id: String, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        TextButton(onClick = onBack, contentPadding = PaddingValues(0.dp)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(Modifier.width(8.dp))
            Text("Back to Portfolio")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Project Case Study: $id", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 8.dp
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "This project represents a deep dive into $id. " +
            "The challenge was to create a seamless experience that scales across multiple platforms. " +
            "By leveraging the power of Compose, we achieved a consistent UI/UX with a single codebase.",
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 26.sp
        )
    }
}

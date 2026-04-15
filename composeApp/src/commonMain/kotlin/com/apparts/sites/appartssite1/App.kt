package com.apparts.sites.appartssite1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.apparts.sites.appartssite1.ui.screens.AboutScreen
import com.apparts.sites.appartssite1.ui.screens.HomeScreen
import com.apparts.sites.appartssite1.ui.screens.ProjectsScreen
import com.apparts.sites.appartssite1.ui.screens.PatentsScreen
import com.apparts.sites.appartssite1.ui.navigation.About
import com.apparts.sites.appartssite1.ui.navigation.Home
import com.apparts.sites.appartssite1.ui.navigation.Patents
import com.apparts.sites.appartssite1.ui.navigation.ProjectDetail
import com.apparts.sites.appartssite1.ui.navigation.Projects
import com.apparts.sites.appartssite1.ui.screens.ProjectDetailScreen
import com.apparts.sites.appartssite1.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        BindNavigationToUrl(navController)
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("App Arts", fontWeight = FontWeight.Bold) },
                    actions = {
                        TextButton(onClick = { navController.navigate(Home) }) { Text("Home") }
                        TextButton(onClick = { navController.navigate(Projects) }) { Text("Projects") }
                        TextButton(onClick = { navController.navigate(Patents) }) { Text("Patents") }
                        TextButton(onClick = { navController.navigate(About) }) { Text("About") }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                AdBanner()
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
                    composable<Home> {
                        HomeScreen(
                            onExploreProjects = {
                                navController.navigate(Projects)
                            },
                            onExplorePatents = {
                                navController.navigate(Patents)
                            }
                        )
                    }
                    composable<Projects> {
                        ProjectsScreen(onNavigateToProject = { id ->
                            navController.navigate(
                                ProjectDetail(id)
                            )
                        })
                    }
                    composable<Patents> {
                        PatentsScreen()
                    }
                    composable<About> { AboutScreen() }
                    composable<ProjectDetail> { backStackEntry ->
                        val route: ProjectDetail = backStackEntry.toRoute()
                        ProjectDetailScreen(
                            route.projectId,
                            onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun AdBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shadowElevation = 8.dp
    ) {
        AdBannerView(modifier = Modifier.fillMaxSize())
    }
}


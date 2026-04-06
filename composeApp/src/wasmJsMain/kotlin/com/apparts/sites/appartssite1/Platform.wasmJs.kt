package com.apparts.sites.appartssite1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.browser.window
import com.apparts.sites.appartssite1.ui.navigation.Home
import com.apparts.sites.appartssite1.ui.navigation.About
import com.apparts.sites.appartssite1.ui.navigation.Projects
import com.apparts.sites.appartssite1.ui.navigation.ProjectDetail
import androidx.navigation.toRoute
import kotlin.js.ExperimentalWasmJsInterop

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

@OptIn(ExperimentalWasmJsInterop::class)
@Composable
actual fun BindNavigationToUrl(navController: NavHostController) {
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            val route = entry.destination.route ?: return@collect
            
            val path = when {
                route.contains("Home") -> "/"
                route.contains("Projects") -> "/projects"
                route.contains("About") -> "/about"
                route.contains("ProjectDetail") -> {
                    // Using a simpler way to detect project detail for URL
                    val id = try {
                         entry.toRoute<ProjectDetail>().projectId
                    } catch (_: Exception) {
                         0
                    }
                    "/project/$id"
                }
                else -> "/"
            }
            
            if (window.location.pathname != path) {
                window.history.pushState(null, "", path)
            }
        }
    }

    LaunchedEffect(Unit) {
        window.onpopstate = {
            val path = window.location.pathname
            when {
                path == "/" || path == "" -> navController.navigate(Home) {
                    popUpTo(Home) { inclusive = true }
                }
                path == "/projects" -> navController.navigate(Projects)
                path == "/about" -> navController.navigate(About)
                path.startsWith("/project/") -> {
                    val id = path.substringAfterLast("/").toIntOrNull() ?: 0
                    navController.navigate(ProjectDetail(id))
                }
            }
        }
    }
}

package com.apparts.sites.appartssite1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.browser.window
import com.apparts.sites.appartssite1.ui.navigation.Home
import com.apparts.sites.appartssite1.ui.navigation.About
import com.apparts.sites.appartssite1.ui.navigation.Patents
import com.apparts.sites.appartssite1.ui.navigation.Projects
import com.apparts.sites.appartssite1.ui.navigation.ProjectDetail
import androidx.navigation.toRoute
import kotlin.js.ExperimentalWasmJsInterop

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

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
                route.contains("Patents") -> "/patents"
                route.contains("About") -> "/about"
                route.contains("ProjectDetail") -> {
                    val id = try {
                         entry.toRoute<ProjectDetail>().projectId
                    } catch (_: Exception) {
                         null
                    }
                    if (id != null) "/project/$id" else null
                }
                else -> null
            }
            
            // Only update the browser history if this route maps to a known path
            // and does not overwrite an external subpath / 404 path
            if (path != null && window.location.pathname != path) {
                window.history.pushState(null, "", path)
            }
        }
    }

    LaunchedEffect(Unit) {
        // Handle initial load and popstate
        fun handlePath(path: String) {
            when {
                path == "/" || path == "" -> navController.navigate(Home) {
                    popUpTo(Home) { inclusive = true }
                }
                path == "/projects" -> navController.navigate(Projects)
                path == "/patents" -> navController.navigate(Patents)
                path == "/about" -> navController.navigate(About)
                path.startsWith("/project/") -> {
                    val id = path.substringAfterLast("/").toIntOrNull()
                    if (id != null) {
                        navController.navigate(ProjectDetail(id))
                    }
                }
                // Unrecognized paths are left as-is without forcing a redirect to Home or rewriting the URL
            }
        }

        handlePath(window.location.pathname)

        window.onpopstate = {
            handlePath(window.location.pathname)
        }
    }
}

actual fun openUrl(url: String) {
    window.open(url, "_blank")
}

@Composable
actual fun AdBannerView(modifier: Modifier) {
    // In a real Web production app, you would use a div and call adsbygoogle.push
    // Since Compose Wasm renders to a canvas, you typically position an HTML element
    // on top of the canvas or use an iframe.
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("Web Ad Slot (AdSense)")
    }
}

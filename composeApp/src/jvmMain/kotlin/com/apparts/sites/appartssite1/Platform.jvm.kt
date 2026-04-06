package com.apparts.sites.appartssite1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun BindNavigationToUrl(navController: NavHostController) {
    // No-op for Desktop
}

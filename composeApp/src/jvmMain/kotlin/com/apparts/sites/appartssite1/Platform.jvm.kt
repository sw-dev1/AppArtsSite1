package com.apparts.sites.appartssite1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import java.awt.Desktop
import java.net.URI

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun BindNavigationToUrl(navController: NavHostController) {
    // No-op for Desktop
}

actual fun openUrl(url: String) {
    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI(url))
    }
}

@Composable
actual fun AdBannerView(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("Desktop Ad Placeholder")
    }
}

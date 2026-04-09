package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.apparts.sites.appartssite1.openUrl
import com.apparts.sites.appartssite1.ui.theme.AppTypography
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProjectDetailScreen(id: Int, onBack: () -> Unit) {
    val project = projects.getOrNull(id)
    if (project == null) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            TextButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                Text("Back")
            }
            Text("Project not found", style = AppTypography.headlineMedium)
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        TextButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            Text("Back")
        }
        Text(
            text = "Project Case Study: ${project.title}",
            style = AppTypography.headlineMedium
        )
        Text(text = project.description, style = AppTypography.bodyMedium)

        if (project.playStoreBadge != null || project.appStoreBadge != null) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                project.playStoreBadge?.let { badge ->
                    Box(modifier = Modifier.clickable { 
                        project.playStoreUrl?.let { openUrl(it) }
                    }) {
                        Image(
                            painter = painterResource(badge),
                            contentDescription = "Google Play",
                            modifier = Modifier.height(44.dp)
                        )
                    }
                }
                project.appStoreBadge?.let { badge ->
                    Box(modifier = Modifier.clickable {
                        project.appStoreUrl?.let { openUrl(it) }
                    }) {
                        Image(
                            painter = painterResource(badge),
                            contentDescription = "App Store",
                            modifier = Modifier.height(44.dp)
                        )
                    }
                }
            }
        }
    }
}
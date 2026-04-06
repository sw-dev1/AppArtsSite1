package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.apparts.sites.appartssite1.ui.theme.AppTypography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "About the Developer",
            style = AppTypography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Passionate software developer specializing in Kotlin Multiplatform, Jetpack Compose, NFC and Bluetooth.",
            style = AppTypography.bodyLarge
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            "Impressum",
            style = AppTypography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """
                App Arts Software Solutions
                Owner: Svilen Tenev
                Address: Korbinian-Beer Str. 11
                80997 München, Germany
                
                Contact:
                Email: info@app-arts.com
                Web: www.apparts.com
                
                Tax ID: DE306125614
            """.trimIndent(),
            style = AppTypography.bodyMedium
        )
    }
}
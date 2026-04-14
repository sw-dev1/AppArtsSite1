package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apparts.sites.appartssite1.ui.theme.AppTypography

@Composable
fun PatentsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Patents & Innovations",
            style = AppTypography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(patents) { patent ->
                PatentCard(patent)
            }
        }
    }
}


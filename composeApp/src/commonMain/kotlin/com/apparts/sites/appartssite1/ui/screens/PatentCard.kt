package com.apparts.sites.appartssite1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.apparts.sites.appartssite1.openUrl
import com.apparts.sites.appartssite1.ui.theme.AppTypography

@Composable
fun PatentCard(patent: Patent) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
            //.clickable { openUrl(patent.url) }
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = patent.number,
                style = AppTypography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = patent.title,
                style = AppTypography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = patent.description,
                style = AppTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            patent.url.let { url ->
                val annotatedString = buildAnnotatedString {
                    append("Link to the documents in the patent office ")
                    withLink(LinkAnnotation.Url(url) { link ->
                        openUrl((link as LinkAnnotation.Url).url)
                    }) {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)) {
                            append("here")
                        }
                    }
                }
                Text(
                    text = annotatedString,
                    style = AppTypography.bodyMedium
                )
            }
        }
    }
}
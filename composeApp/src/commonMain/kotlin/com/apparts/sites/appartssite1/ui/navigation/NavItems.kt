package com.apparts.sites.appartssite1.ui.navigation

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

@Serializable
object Home

@Serializable
object Projects

@Serializable
object About

@Serializable
data class ProjectDetail(val projectId: Int)

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val playStoreUrl: String? = null,
    val appStoreUrl: String? = null,
    val playStoreBadge: DrawableResource? = null,
    val appStoreBadge: DrawableResource? = null
)

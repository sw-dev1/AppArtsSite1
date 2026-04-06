package com.apparts.sites.appartssite1.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Projects

@Serializable
object About

@Serializable
data class ProjectDetail(val projectId: String)
data class Project(val id: String,
                   val title: String,
                   val description: String,
                   val category: String)
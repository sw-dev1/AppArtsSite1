package com.apparts.sites.appartssite1.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Projects

@Serializable
object Patents

@Serializable
object About

@Serializable
data class ProjectDetail(val projectId: Int)


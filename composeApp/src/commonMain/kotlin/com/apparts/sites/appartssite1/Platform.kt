package com.apparts.sites.appartssite1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun BindNavigationToUrl(navController: NavHostController)

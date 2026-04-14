package com.apparts.sites.appartssite1.ui.screens

import appartssite1.composeapp.generated.resources.Res
import appartssite1.composeapp.generated.resources.app_store_badge_en
import appartssite1.composeapp.generated.resources.google_play_store_badge_en
import org.jetbrains.compose.resources.DrawableResource


data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val projectUrl: String? = null,
    val playStoreUrl: String? = null,
    val appStoreUrl: String? = null,
    val playStoreBadge: DrawableResource? = null,
    val appStoreBadge: DrawableResource? = null
)

val projects = listOf(
    Project(
        id = 0,
        title = "This Web Site",
        description = "Developed with Kotlin Multiplatform WASM, served from GitHub Pages",
        category = "KMP",
        projectUrl = "https://github.com/sw-dev1/AppArtsSite1"
    ),
    Project(
        id = 1,
        title = "Groceries with NFC",
        description = "Home pantry management with NFC tags",
        category = "KMP, Android, iOS",
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.apparts.util.groceries&pli=1",
        appStoreUrl = "https://www.apple.com/app-store/",
        playStoreBadge = Res.drawable.google_play_store_badge_en,
        appStoreBadge = Res.drawable.app_store_badge_en
    ),
    Project(
        id = 2,
        title = "Library with NFC",
        description = "A helper for managing NFC-tagged library items",
        category = "KMP, Android, iOS"
    ),
    Project(
        id = 3,
        title = "Where is the Car?",
        description = "Find your car parked in the neighbourhood",
        category = "KMP, Android, iOS"
    )
)

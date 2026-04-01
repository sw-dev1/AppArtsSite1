package com.apparts.sites.appartssite1

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "AppArtsSite1",
    ) {
        App()
    }
}
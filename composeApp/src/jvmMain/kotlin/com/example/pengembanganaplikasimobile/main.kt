package com.example.pengembanganaplikasimobile

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import navigation.AppNavigation

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PengembanganAplikasiMobile",
    ) {
        AppNavigation()
    }
}

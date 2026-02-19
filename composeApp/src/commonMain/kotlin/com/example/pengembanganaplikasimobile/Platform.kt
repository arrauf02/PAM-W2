package com.example.pengembanganaplikasimobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
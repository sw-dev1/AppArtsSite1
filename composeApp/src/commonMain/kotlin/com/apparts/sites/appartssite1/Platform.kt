package com.apparts.sites.appartssite1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
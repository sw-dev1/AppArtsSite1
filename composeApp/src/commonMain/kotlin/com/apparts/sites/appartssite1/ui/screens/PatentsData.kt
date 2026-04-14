package com.apparts.sites.appartssite1.ui.screens

data class Patent(
    val id: Int,
    val title: String,
    val number: String,
    val description: String,
    val url: String
)

val patents = listOf(
    Patent(
        id = 0,
        title = "User management for identification systems",
        number = "10 2022 105 680.5",
        description = "The invention consists of the idea about how to incorporate existing user/account management subsystems into complete identification systems by retrieving and implementing user/account IDs throughout the identification, authentication and authorization workflows. The idea enables component providers to build complete systems by reusing existing account management subsystems.",
        url = "https://register.dpma.de/DPMAregister/pat/register?lang=en&fromSprachWechselLink"
    ),
    Patent(
        id = 1,
        title = "Satisfying Contradictory Requirements",
        number = "10 2022 105 685.6",
        description = "Function/Goal: create a system where the same components interact differently in order to implement independent applications that place contradictory requirements on the components.",
        url = "https://register.dpma.de/DPMAregister/pat/register?lang=en&fromSprachWechselLink"
    ),
    Patent(
        id = 2,
        title = "Predict values without measuring",
        number = "10 2022 105 687.2",
        description = "Function/Goal: Determine/predict strongly varying device-specific values by using common characteristics and relating measured values to the unknown values.",
        url = "https://register.dpma.de/DPMAregister/pat/register?lang=en&fromSprachWechselLink"
    )
)

package com.apparts.sites.appartssite1.ui.screens

import com.apparts.sites.appartssite1.ui.navigation.Project

//The project id equals the index in the list, so that projects[id] and project.id always return the same
val projects = listOf(
    Triple("This Web Site", "Developed with Kotlin Multiplatform WASM", "KMP"),
    Triple("Groceries with NFC", "Home pantry management with NFC tags", "KMP, Android, iOS"),
    Triple("Library with NFC", "A helper for managing NFC-tagged library items", "KMP, Android, iOS"),
    Triple("Where is the Car?", "Find your car parked in the neighbourhood", "KMP, Android, iOS")
).mapIndexed { index, (title, desc, cat) ->
    Project(
        id = index,
        title = title,
        description = desc,
        category = cat
    )
}

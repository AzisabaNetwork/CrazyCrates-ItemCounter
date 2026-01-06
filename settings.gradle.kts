rootProject.name = "CrazyCrates"

include(":crazycrates-api")
project(":crazycrates-api").projectDir = file("api")

include(":crazycrates-paper")
project(":crazycrates-paper").projectDir = file("platforms/paper")
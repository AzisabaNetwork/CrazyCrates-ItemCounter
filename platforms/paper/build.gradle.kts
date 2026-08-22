@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazycrates.paper-plugin")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.codemc.io/repository/maven-public/") // DecentHolograms, PlaceholderAPI
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/") // PlaceholderAPI
    maven("https://nexus.phoenixdevt.fr/repository/maven-public/") // MMOItems
    maven("https://repo.nexomc.com/repository/maven-public/") // ItemsAdder
}

dependencies {
    shadow(project(":crazycrates-api"))
    compileOnly(libs.papermc)

    // 重要：フォルダ内のJARを強制的に読み込む
    // 'libs' は build.gradle.kts と同じ階層にある前提です
    compileOnly(fileTree("libs") { include("*.jar") })

    // 万が一上の行でダメな場合のために、一階層上も見に行く設定
    compileOnly(fileTree("../../libs") { include("*.jar") })

    // 1.16.5用のAdventure
    shadow("net.kyori:adventure-api:4.14.0")
}
tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName.set("CrazyCrates-Paper.jar")
        configurations = listOf(project.configurations.getByName("shadow"))

        // 以前のリロケートが残っているとマズいので一旦無効、または最小限に
        mergeServiceFiles()
    }

    assemble {
        dependsOn(shadowJar)
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        from(sourceSets.main.get().resources.srcDirs) {
            expand(
                "name" to "CrazyCrates",
                "group" to "com.badbones69.crazycrates",
                "version" to "1.0.0",
                "description" to "Crates Plugin",
                "website" to "https://github.com/pino223-72/UltraCrazyCrates"
            )
            include("plugin.yml")
        }
    }
}

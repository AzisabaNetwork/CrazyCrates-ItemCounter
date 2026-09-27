plugins {
    java
    `java-library`
}

description = "Add unlimited crates to your server with 9 different crate types to choose from!"

allprojects {
    group = "com.badbones69.crazycrates"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://jitpack.io")
        maven("https://nexus.phoenixdevt.fr/repository/maven-public/")
    }

    // 全プロジェクトで文字コードをUTF-8に固定（ビルドエラー解消用）
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}

dependencies {
    implementation(project(":crazycrates-api"))
    implementation(project(":crazycrates-paper"))
}

tasks {
    jar {
        enabled = false
    }
}

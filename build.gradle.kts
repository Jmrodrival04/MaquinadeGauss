// build.gradle.kts (Project level)
plugins {
    // Asegúrate de que la versión de Kotlin sea consistente aquí
    kotlin("android") version "1.9.10" apply false
    id("com.android.application") version "8.1.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

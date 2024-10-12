// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Asegúrate de que la versión de Kotlin sea consistente aquí
    kotlin("android") version "1.9.10" apply false
    id("com.android.application") version "8.1.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false // Plugin para Firebase
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

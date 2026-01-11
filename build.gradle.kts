// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // CORRECCIÓN 1: Usamos la versión exacta compatible con tu Kotlin 2.0.21
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false

    // CORRECCIÓN 2: Unificamos Google Services (borra el bloque buildscript de abajo)
    id("com.google.gms.google-services") version "4.4.2" apply false
    //id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
}
plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    //ROOM
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ksp) apply false
    //Cocoapods
    alias(libs.plugins.kotlin.cocoapods) apply false
}
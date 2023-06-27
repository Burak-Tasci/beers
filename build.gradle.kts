// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(Dependencies.Navigation.safeArgs)
    }
}
plugins {
    id("com.android.application") version Version.Others.gradle apply false
    id("com.android.library") version Version.Others.gradle apply false
    id("org.jetbrains.kotlin.android") version Version.Others.kotlin apply false
    id("com.google.dagger.hilt.android") version Version.Dagger.hilt apply false
}
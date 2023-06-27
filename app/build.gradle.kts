@file:Suppress("UnstableApiUsage")

import Dependencies.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = Configuration.namespace
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = Configuration.applicationId
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = Configuration.testInstrumentationRunner
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    viewBinding.enable = true
    dataBinding.enable = true
    buildFeatures.buildConfig = true

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(Others.material)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // test libraries
    testImplementation(JUnit.jUnit)
    androidTestImplementation(AndroidX.testExtJUnit)
    androidTestImplementation(AndroidX.testEspressoCore)

    // Hilt - Dependency Injection
    implementation(Dagger.hilt)
    kapt(Dagger.hiltCompiler)

    // Navigation Component
    implementation(Navigation.fragment)
    implementation(Navigation.ui)

    // Http client for api requests
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConverter)

    // Request response logging
    implementation(OkHttp.loggingInterceptor)

    // Image Loading
    implementation(Picasso.picasso)


}
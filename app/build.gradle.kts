apply(plugin = "kotlin-kapt") // Enables annotation processing required by Hilt (e.g., @Inject, @HiltAndroidApp)
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)  // Applies Hilt plugin for dependency injection
    id("org.jetbrains.kotlin.kapt") // Required for kapt annotation processor for Hilt
}

android {
    namespace = "com.cambly.bibleverseviewer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cambly.bibleverseviewer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    hilt {
        enableAggregatingTask = false // Optional: Optimizes Hilt build process by avoiding aggregating task
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt
    implementation(libs.hilt.android) // Hilt runtime for DI
    kapt(libs.hilt.compiler) // Compiles DI code using kapt
    implementation(libs.hilt.navigation.compose) // Integrates Hilt with Jetpack Compose navigation

    // Retrofit
    implementation(libs.retrofit) // Retrofit for HTTP requests
    implementation(libs.converter.gson) // Gson converter for parsing JSON responses

}
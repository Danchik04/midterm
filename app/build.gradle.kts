plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.midterm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.midterm"
        minSdk = 24
        targetSdk = 34
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
    configurations.all {
        exclude(group = "com.intellij", module = "annotations")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx.v1160)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material.v1110)
    implementation(libs.androidx.constraintlayout.v214)

    // Architecture Components
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v270)
    implementation(libs.androidx.lifecycle.livedata.ktx.v287)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Room
    implementation(libs.androidx.room.runtime.v261)
    implementation(libs.androidx.room.ktx.v261)
    implementation(libs.androidx.room.compiler.v261)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Hilt
    implementation(libs.hilt.android.v248)
    implementation(libs.hilt.android.compiler.v248)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.navigation.runtime.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.ui.text.android)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.material3.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
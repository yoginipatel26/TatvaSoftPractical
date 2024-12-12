plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.yoginipatelpractical"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yoginipatelpractical"
        minSdk = 26
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
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.3.0")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$2.6.1")
// ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$2.6.1")
// LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$2.6.1")

    implementation ("com.github.bumptech.glide:glide:4.13.2")


        val room_version = "2.6.1"

    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.16")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("androidx.room:room-runtime:2.5.1")  // Or the version you're using
    implementation ("androidx.room:room-ktx:2.5.1")

    //hilt
    implementation ("com.google.dagger:hilt-android:2.51.1")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")




}
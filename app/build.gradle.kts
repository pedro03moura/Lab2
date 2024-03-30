plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.lab2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab2"
        minSdk = 26
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

    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
}
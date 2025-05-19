import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.workclass"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.workclass"
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
}

dependencies {

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.adaptive.android)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.coil.compose)
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.camera.view)


    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // retrofit


    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.converter.gson)
    implementation (libs.androidx.lifecycle.runtime.ktx.v262)
    implementation (libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose) // Mejor integración
    implementation(libs.androidx.core.ktx) // Para ContentResolver
    implementation(libs.androidx.activity.compose) // Para ActivityResultLauncher
    implementation(libs.androidx.activity.ktx) // Para contextos

    implementation ("androidx.biometric:biometric:1.2.0-alpha05") //sensor

    // Para manejo de calendario

    val room_version = "2.6.1"


    implementation(libs.androidx.room.runtime)
    implementation("androidx.room:room-ktx:$room_version")
    ksp(libs.androidx.room.compiler)
}
ksp{
    arg("room.schemaLocation", "$projectDir/schemas")
}

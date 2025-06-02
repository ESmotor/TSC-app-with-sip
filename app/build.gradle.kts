plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.gms)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.itskidan.tscapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.itskidan.tscapp"
        minSdk = 28
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
            buildConfigField("String", "NOTIFY_FCM_BASE_URL", "\"https://europe-west3-tsc-linphonefcm.cloudfunctions.net/\"")
        }
        debug {
            applicationIdSuffix = null
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
            buildConfigField("String", "NOTIFY_FCM_BASE_URL", "\"https://europe-west3-tsc-linphonefcm.cloudfunctions.net/\"")
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
        buildConfig = true
    }
}

dependencies {
    // modules
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Google
    implementation(libs.androidx.compose.ui.text.google.fonts)
    //implementation (libs.play.services.fonts)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    // Hilt. For instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)
    // Hilt. For local unit tests
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)
    // Timber
    implementation(libs.timber)
    // linphone
    implementation(libs.linphone.sdk)
    // Material 3
    implementation(libs.androidx.compose.material3.windowSizeClass)
    // material-icon
    implementation(libs.androidx.compose.material.iconsExtended)
    // Coil
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.svg)
    // For basic components
    implementation(libs.androidx.compose.foundation)
    // Accompanist
    implementation(libs.accompanist.permissions)
    // WorkManager
    implementation (libs.androidx.work.runtime.ktx)
    implementation (libs.androidx.hilt.work)

}


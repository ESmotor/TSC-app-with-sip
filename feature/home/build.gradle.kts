plugins {
    alias(libs.plugins.tscapp.android.feature)
    alias(libs.plugins.tscapp.android.library.compose)
//    alias(libs.plugins.tscapp.android.library.jacoco)
//    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.itskidan.feature.home"

}

dependencies {
    implementation(libs.accompanist.permissions)

}
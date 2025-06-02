plugins {
    `kotlin-dsl`
}

group = "com.itskidan.tscapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)

}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        // Will add in next step
        register("androidApplicationCompose") {
            id = libs.plugins.tscapp.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.tscapp.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.tscapp.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.tscapp.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.tscapp.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.tscapp.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.tscapp.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }

    }
}
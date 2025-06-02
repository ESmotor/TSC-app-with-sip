import com.android.build.gradle.LibraryExtension
import com.itskidan.tscapp.app.configureGradleManagedDevices
import com.itskidan.tscapp.app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "tscapp.android.library")
            apply(plugin = "tscapp.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }

            dependencies {
//                "implementation"(project(":core:ui"))
//                "implementation"(project(":core:designsystem"))
//
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.tracing.ktx").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())

                "testImplementation"(libs.findLibrary("androidx.navigation.testing").get())
                "androidTestImplementation"(
                    libs.findLibrary("androidx.lifecycle.runtimeTesting").get(),
                )
            }
        }
    }
}

//// Hilt
//implementation(libs.hilt.android)
//kapt(libs.hilt.compiler)
//implementation(libs.androidx.hilt.navigation.compose)
//// Hilt. For instrumentation tests
//androidTestImplementation(libs.hilt.android.testing)
//kaptAndroidTest(libs.hilt.compiler)
//// Hilt. For local unit tests
//testImplementation (libs.hilt.android.testing)
//kaptTest (libs.hilt.compiler)
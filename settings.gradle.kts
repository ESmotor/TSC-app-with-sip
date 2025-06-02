pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "linphone.org maven repository"
            url = uri("https://download.linphone.org/maven_repository")
            content {
                includeGroup("org.linphone")
            }
        }
    }
}

rootProject.name = "TSCapp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":domain")
include(":data")
include(":feature:home")

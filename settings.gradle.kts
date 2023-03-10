pluginManagement {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven ( url= "https://jitpack.io")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven ( url= "https://jitpack.io")
        mavenCentral()
        jcenter()
    }
}
rootProject.name = "template"
include (":app")

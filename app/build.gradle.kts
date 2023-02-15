
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.gsm)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.template"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.template"
        minSdk = 24
        targetSdk = 33
        compileSdk=33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        kapt {
            correctErrorTypes = true
        }

    }



    buildTypes {
        getByName("debug"){

        }
        getByName("release") {
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
    buildFeatures {
        compose = true
        dataBinding=true
        aidl = false
        buildConfig = true
        renderScript = false
        shaders = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    //
    implementation(libs.com.google.firebase.fcm)
    //
    implementation(libs.com.google.accompanist.permission)
    implementation(libs.com.google.accompanist)
    //materialIcon
    implementation(libs.androidx.compose.material)
    //navbar
    implementation(libs.androidx.compose.material.navbar)
    //Serialization
    implementation(libs.kotlin.serialization.json)
    //
    implementation(libs.androidx.lifecycle)
    //firebase
    implementation(platform(libs.com.google.firebase))
    implementation(libs.com.google.firebase.auth.ktx)
    implementation(libs.com.google.firebase.realtime)
    // preference data store
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.security)
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    //navigation
    implementation(libs.androidx.navigation.compose)
    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    implementation("androidx.core:core-ktx:+")
    kapt(libs.hilt.compiler)
    // Hilt and instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    // Hilt and Robolectric tests.
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)

    // Arch Components
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    //networking
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.okhttp3.interceptor)
    implementation(libs.com.squareup.retrofit2.gson)
    implementation(libs.com.jakewharton.timber)

    // coil
    implementation(libs.io.coil.kt.coil)
    implementation(libs.io.coil.kt.coil.compose)
    // Tooling
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Instrumented tests
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Local tests: jUnit, coroutines, Android runner
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumented tests: jUnit rules and runners
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
}
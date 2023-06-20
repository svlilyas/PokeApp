import ModuleDependency.Project.core_model

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.stack.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.papirus.androidbase.core.uicomponents"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(core_model())

    implementation(libs.stack.coil)

    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.stack.timber)
    implementation(libs.stack.kotlin.reflect)
}
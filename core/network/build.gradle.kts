import ModuleDependency.Project.core_model
import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.stack.kotlin.kapt.get().pluginId)
    id(libs.plugins.stack.kotlin.parcelize.get().pluginId)
    id(libs.plugins.stack.hilt.plugin.get().pluginId)
    id(libs.plugins.stack.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    buildTypes {
        debug {
            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://pokeapi.co/")
        }
        release {
            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://pokeapi.co/")
        }
    }

    namespace = "com.papirus.androidbase.core.network"
}

dependencies {
    implementation(core_model())

    implementation(libs.stack.okhttp.interceptor)

    implementation(libs.stack.sandwich)

    implementation(libs.stack.timber)

    // coroutines
    implementation(libs.stack.coroutines)

    //moshi
    implementation(libs.stack.converter.moshi)
    implementation(libs.stack.moshi.kotlin)

    // network
    implementation(libs.stack.retrofit.core)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}
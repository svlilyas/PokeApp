object AndroidConfig {
    const val appName = "AndroidBase"
    const val namespace = "com.papirus.androidbase"
    const val applicationId = "com.papirus.androidbase"
    const val minSdk = 24
    const val targetSdk = 33
    const val buildTools = "30.0.3"
    const val compileSdk = 33
    const val ndk = "23.0.7599858"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val jvmTarget = "11"

    const val versionCode = 1

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    val versionName = "v$versionMajor.$versionMinor.$versionPatch"
}

object Flavors {
    object ProductFlavors {
        const val DEV = "development"
        const val PROD = "production"
    }

    object FlavorDimensions {
        const val ENVIRONMENT = "environment"
    }
}

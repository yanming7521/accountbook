val versionC = 2
val versionS = "v1.0.1"
val versionO = "V1_0_1"
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.accountbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.accountbook"
        minSdk = 24
        targetSdk = 34
        versionCode = versionC
        versionName = versionS

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    this.buildOutputs.all {
        val variantOutputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
        val outputFileName = "accountbook_${variantOutputImpl.name}_${versionO}.apk"
        variantOutputImpl.outputFileName = outputFileName
    }
    buildTypes {
        release {
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
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // 数据库
    implementation(files("libs/lite-orm-1.8.1.jar"))

}
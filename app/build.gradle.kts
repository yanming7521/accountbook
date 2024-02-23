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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

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
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.6.2")
    implementation("androidx.compose.ui:ui-tooling:1.6.2")
    implementation("androidx.compose.foundation:foundation:1.6.2")
    implementation("androidx.compose.material:material:1.6.2")
    implementation("androidx.compose.material:material-icons-core:1.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // 数据库
    implementation(files("libs/lite-orm-1.8.1.ar"))
    // flex布局
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    // 自动适配
    implementation("me.jessyan:autosize:1.2.1")
    // 通用工具包
    implementation("com.blankj:utilcodex:1.31.1")
    // 图片加载
    implementation("io.coil-kt:coil:2.5.0")
    implementation("io.coil-kt:coil-video:2.5.0")
    implementation("androidx.palette:palette:1.0.0")
    // recyclerView 适配器
    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4")
    // mmkv
    implementation("com.tencent:mmkv:1.3.0")
    // 日历组件
    implementation("com.haibin:calendarview:3.7.1")
    // 图表
    implementation("com.github.PhilJay:MPAndroidChart:3.1.0")
    // Net
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.github.liangjingkanji:Net:3.5.1")
    implementation("com.github.liangjingkanji:Tooltip:1.2.2")
    // json
    implementation("com.google.code.gson:gson:2.9.1")
}
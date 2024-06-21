plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs")
    kotlin("plugin.serialization") version "1.7.0"
    id("com.google.devtools.ksp")
}

android {
    namespace = "test.em.tickets"
    compileSdk = 34

    defaultConfig {
        applicationId = "test.em.tickets"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/v3/\"")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation (libs.lifecycle.viewmodel.savedstate)
    implementation (libs.androidx.paging.runtime.ktx)

    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation (libs.moshi)
    implementation (libs.squareup.moshi.kotlin)
    implementation (libs.retrofit2.retrofit.mock)
    implementation (libs.logging.interceptor)
    implementation (libs.retrofit2.converter.gson)

    implementation (libs.play.services.location)

    implementation (libs.jackson.module.kotlin)
    implementation (libs.gson)
    implementation(libs.json)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(kotlin("test"))

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    ksp(libs.androidx.room.room.compiler2)

    implementation (libs.viewbindingpropertydelegate.noreflection)

}
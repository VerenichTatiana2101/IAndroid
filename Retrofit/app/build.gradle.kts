plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp").version("1.6.10-1.0.4") // Or latest version of KSP
}

android {
    namespace = "com.example.retrofit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.retrofit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    //moshi
//    implementation(libs.moshi)
//    implementation(libs.squareup.converter.moshi)
    ksp(libs.moshi.kotlin.codegen)

    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with Moshi Converter
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi.v290)

    // Gson
    implementation(libs.converter.gson)
    // Coil
    implementation(libs.coil)

    //Glide
    implementation(libs.github.glide)
    ksp(libs.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
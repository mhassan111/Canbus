package com.canbus.app.buildsrc

object ConfigData {
    const val compileSdkVersion = 31
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {

    object Plugins {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.4"
        const val googlePlayServicesPlugin = "com.google.gms:google-services:4.3.10"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val firebaseCrashlyticsPlugin =
            "com.google.firebase:firebase-crashlytics-gradle:2.8.1"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
    }

    object Accompanist {
        const val accompanist_inset_version = "0.22.0-rc"
        const val accompanist_permission_version = "0.21.1-beta"
        const val accompanist_system_ui_controller_version = "0.17.0"
        const val insets = "com.google.accompanist:accompanist-insets:$accompanist_inset_version"
        const val permissions = "com.google.accompanist:accompanist-permissions:$accompanist_permission_version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$accompanist_system_ui_controller_version"
    }

    object Kotlin {
        const val version = "1.6.10"
        const val ktx_version = "1.7.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val kts = "androidx.core:core-ktx:$ktx_version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Hilt {
        private const val hilt_version = "2.38.1"
        private const val hilt_viewModel_version = "1.0.0-alpha03"
        private const val hilt_compiler_version = "1.0.0"
        private const val hilt_navigation_compose_version = "1.0.0-rc01"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version"
        const val dagger_hilt_android = "com.google.dagger:hilt-android:$hilt_version"
        const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:$hilt_version"
        const val hilt_view_model = "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_viewModel_version"
        const val hilt_compiler = "androidx.hilt:hilt-compiler:$hilt_compiler_version"
    }

    object Room {
        private const val room_version = "2.4.0"
        const val room_runtime = "androidx.room:room-runtime:$room_version"
        const val room_compiler = "androidx.room:room-compiler:$room_version"
        const val coroutine_support = "androidx.room:room-ktx:$room_version"
    }

    object Firebase {
        private const val firebase_crashlytics_version = "18.2.6"
        private const val firebase_analytics_version = "20.0.2"
        private const val firebase_messaging_version = "23.0.0"
        private const val firebase_firestore_version = "24.0.1"
        private const val firebase_auth_version = "21.0.1"

        const val firebase_crashlytics =
            "com.google.firebase:firebase-crashlytics:$firebase_crashlytics_version"
        const val firebase_analytics =
            "com.google.firebase:firebase-analytics:$firebase_analytics_version"
        const val firebase_messaging =
            "com.google.firebase:firebase-messaging:$firebase_messaging_version"
        const val firebase_auth = "com.google.firebase:firebase-auth:$firebase_auth_version"
        const val firebase_firestore = "com.google.firebase:firebase-firestore:$firebase_firestore_version"
    }

    object Retrofit {
        private const val retrofit_version = "2.9.0"
        private const val logging_interceptor_version = "4.7.2"
        private const val retrofit_rxjava_adapter_version = "3.0.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val gson_converter = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version"
        const val rxjava_adapter =
            "com.github.akarnokd:rxjava3-retrofit-adapter:$retrofit_rxjava_adapter_version"
    }

    object Okhttp {
        private const val ok_http_version = "2.7.5"
        const val ok_http = "com.squareup.okhttp:okhttp:$ok_http_version"
    }

    object AndroidX {

        const val appCompat = "androidx.appcompat:appcompat:1.4.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-rc01"

        object Compose {
            const val snapshot = ""
            const val compose_version = "1.0.5"
            private const val compose_view_model = "1.0.0-alpha07"
            private const val navigation_version = "2.4.0-rc01"
            private const val compose_foundation_version = "1.2.0-alpha08"

            const val navigation = "androidx.navigation:navigation-compose:$navigation_version"
            const val animation = "androidx.compose.animation:animation:$compose_version"
            const val foundation = "androidx.compose.foundation:foundation:$compose_foundation_version"
            const val layout = "androidx.compose.foundation:foundation-layout:$compose_version"
            const val iconsExtended =
                "androidx.compose.material:material-icons-extended:$compose_version"
            const val material = "androidx.compose.material:material:$compose_version"
            const val runtime = "androidx.compose.runtime:runtime:$compose_version"
            const val tooling = "androidx.compose.ui:ui-tooling:$compose_version"
            const val ui = "androidx.compose.ui:ui:$compose_version"
            const val activity = "androidx.activity:activity-compose:$compose_version"
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel-compose:$compose_view_model"
            const val uiUtil = "androidx.compose.ui:ui-util:$compose_version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$compose_version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$compose_version"

            object Library {
                const val compose_destination_version = "1.1.3-beta"
                const val raamcosta_compose_detination =
                    "io.github.raamcosta.compose-destinations:core:$compose_destination_version"
                const val raamcosta_ksp =
                    "io.github.raamcosta.compose-destinations:ksp:$compose_destination_version"
            }
        }
    }

    object Test {
        private const val version = "1.4.0"
        private const val junit_version = "4.13"
        private const val ext_junit_version = "1.1.3"
        private const val espresso_version = "3.4.0"

        const val junit = "junit:junit:$junit_version"
        const val extJunit = "androidx.test.ext:junit:$ext_junit_version"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:$espresso_version"
        const val composeUiTest =
            "androidx.compose.ui:ui-test-junit4:${AndroidX.Compose.compose_version}"
        const val composeToolingTest =
            "androidx.compose.ui:ui-tooling:${AndroidX.Compose.compose_version}"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
    }

    object LibPhone {
        const val libPhoneNumber = "com.googlecode.libphonenumber:libphonenumber:8.2.0"
    }
}

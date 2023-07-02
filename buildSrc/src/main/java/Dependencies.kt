/**
 * Created by tasci on 22.06.2023.
 */
class Dependencies {
    object AndroidX{
        const val core = "androidx.core:core-ktx:${Version.AndroidX.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.AndroidX.appCompat}"
        const val testExtJUnit = "androidx.test.ext:junit:${Version.AndroidX.textExtJUnit}"
        const val testEspressoCore = "androidx.test.espresso:espresso-core:${Version.AndroidX.textEspressoCore}"
        const val splashScreen = "androidx.core:core-splashscreen:${Version.AndroidX.splashScreen}"
    }

    object JUnit{
        const val jUnit = "junit:junit:${Version.JUnit.jUnit}"
    }

    object Dagger{
        const val hilt = "com.google.dagger:hilt-android:${Version.Dagger.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.Dagger.hilt}"
    }

    object Navigation{
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Version.Navigation.navigationComponent}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Version.Navigation.navigationComponent}"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.Navigation.navigationComponent}"
    }

    object Retrofit{
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.Retrofit.retrofit}"
    }

    object Picasso{
        const val picasso = "com.squareup.picasso:picasso:${Version.Picasso.picasso}"
    }

    object Others{
        const val material = "com.google.android.material:material:${Version.Others.material}"
    }
    object OkHttp{
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.OkHttp.loggingInterceptor}"
    }
}
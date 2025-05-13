package com.example.login_kt2

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.login_kt2.repository.LoginTryRepository
import com.example.login_kt2.repository.room.AppDatabase

/**
 * REQUIREMENT:
 * You need to specify attribute android:name=".AppApplication" in AndroidManifest.xml
 * Otherwise, this class is not initialized
 */
class AppApplication : Application() {

    // instance to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        Log.i("AppApplication", "onCreate")
    }
}

/**
 * We want to limit the visibility of Android-related objects to ViewModels and Composable.
 *
 * So, we attach here the repositories to a GameApplication object
 *    so that we can retrieve them in the AppViewModelProvider.
 */
class AppContainer(private val context: Context) {
    val loginTryRepository : LoginTryRepository by lazy {
        LoginTryRepository(AppDatabase.getDatabase(context).loginTryDao())
    }
}
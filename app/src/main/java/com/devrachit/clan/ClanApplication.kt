package com.devrachit.clan

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point annotated with [HiltAndroidApp] to trigger
 * Hilt's code generation and serve as the application-level dependency container.
 */
@HiltAndroidApp
class ClanApplication : Application()

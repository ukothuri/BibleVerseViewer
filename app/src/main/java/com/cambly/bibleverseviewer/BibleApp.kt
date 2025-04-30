package com.cambly.bibleverseviewer


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Informs Hilt to generate DI components
class BibleApp : Application() // Custom Application class to bootstrap Hilt

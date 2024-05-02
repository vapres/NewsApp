package com.route.test

import android.app.Application
import com.route.test.api.Constants

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
    }
}
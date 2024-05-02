package com.route.test.activities.splash

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    var delegate: OnSplashNavigation? = null
    fun navigateToHomeActivity() {
        if (delegate != null) {
            delegate?.navigateToHomeActivity()
        }
    }

}

fun interface OnSplashNavigation {
    fun navigateToHomeActivity()

}

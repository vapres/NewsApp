package com.route.test.api

import android.content.Context
import com.route.test.model.Category
import com.route.test.R
import java.util.Properties

object Constants {
    private val properties = Properties()
    fun getApiKey(): String? {
        return properties.getProperty("API_KEY")
    }

    fun init(context: Context) {
        val inputStream = context.resources.openRawResource(R.raw.secrets)
        properties.load(inputStream)
    }
}

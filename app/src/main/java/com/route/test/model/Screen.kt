package com.route.test.model

import com.route.test.R

open class Screen(val route: String, val destination: Int)

class News : Screen("news", R.string.app_name)

class Categories : Screen("categories", R.string.categories)
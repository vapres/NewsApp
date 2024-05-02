package com.route.test.model

import com.route.test.R

data class Category(
    val titleResId: Int,
    val drawableResId: Int,
    val backgroundColor: Int,
    val apiId: String
)

val categoriesList = listOf<Category>(
        Category(
            R.string.sports,
            R.drawable.ball,
            R.color.red,
            "sports"
        ) ,
        Category(
            R.string.technology,
            R.drawable.politics,
            R.color.blue_dark,
            "technology"
        ), Category(
            R.string.health,
            R.drawable.health,
            R.color.pink,
            "health"
        ), Category(
            R.string.business,
            R.drawable.bussines,
            R.color.brown,
            "business"
        ), Category(
            R.string.general,
            R.drawable.environment,
            R.color.blue,
            "general"
        ), Category(
            R.string.science,
            R.drawable.science,
            R.color.yellow,
            "science"
        )
    )


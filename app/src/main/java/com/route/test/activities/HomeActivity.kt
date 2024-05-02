package com.route.test.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.test.R
import com.route.test.ui.theme.TestTheme
import com.route.test.utils.fragments.categories.CategoriesFragment
import com.route.test.utils.fragments.newsdetails.NewsDetailsScreen
import com.route.test.widgets.NavigationDrawerSheet
import com.route.test.utils.fragments.news.NewsScreen
import com.route.test.utils.fragments.search.SearchScreen
import com.route.test.utils.fragments.settings.SettingsScreen
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                NavigationDrawer()
                // A surface container using the 'background' color from the theme
            }
        }
    }

    fun openWebsiteForNews(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerSheet(
                onCategoriesClick = {
                    navController.popBackStack()
                    if (navController.currentDestination?.route != "categories")
                        navController.navigate("categories")

                    scope.launch {
                        drawerState.close()
                    }
                },
                onSettingsClick = {
                    navController.popBackStack()
                    if (navController.currentDestination?.route != "settings")
                        navController.navigate("settings")

                    scope.launch {
                        drawerState.close()
                    }
                })
        }
    ) {
        Column {
            NavHost(
                navController = navController,
                startDestination = "categories"
            ) {
                composable("categories") {

                    CategoriesFragment(
                        viewModel(),
                        scope,
                        drawerState
                    )
                    { categoryId, categoryName ->
                        navController.navigate("news/$categoryId/$categoryName")
                    }
                }
                composable(
                    "news/{categoryId}/{categoryName}",
                    arguments = listOf(navArgument("categoryId") {
                        type = NavType.StringType
                    }, navArgument("categoryName") {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    val categoryId = backStackEntry.arguments?.getString("categoryId") ?: "sports"
                    val categoryName =
                        backStackEntry.arguments?.getInt("categoryName") ?: R.string.sports
                    NewsScreen(
                        viewModel(),
                        categoryId,
                        categoryName,
                        scope,
                        drawerState,
                        onNewsClick = { title ->
                            navController.navigate("newsDetails/$title")
                        },
                        onSearchClick = {
                            navController.navigate("search")
                        }
                    )
                }

                composable(route = "newsDetails/{title}", arguments = listOf(navArgument("title") {
                    type = NavType.StringType
                })) { navBackStackEntry ->
                    val title = navBackStackEntry.arguments?.getString("title") ?: ""
                    NewsDetailsScreen(viewModel(), title)
                }

                composable(route = "search") {
                    SearchScreen(viewModel()) { title ->
                        navController.navigate("newsDetails/$title")
                    }
                }

                composable(route = "settings") {
                    SettingsScreen(viewModel(),scope, drawerState)
                }
            }
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewNewsScreen() {
    NavigationDrawer()
}









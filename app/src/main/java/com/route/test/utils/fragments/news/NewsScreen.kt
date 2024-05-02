package com.route.test.utils.fragments.news


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.test.R
import com.route.test.widgets.NewsList
import com.route.test.widgets.NewsTopAppBar
import com.route.test.widgets.SourcesTabRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NewsScreen(
    vm: NewsViewModel,
    categoryID: String,
    categoryName: Int,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onNewsClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {

    Scaffold(
        topBar =
        {
            NewsTopAppBar(
                titleResId = categoryName,
                shouldDisplayMenuIcon = true,
                shouldDisplaySearchIcon = true,
                onSideMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onSearchClick = onSearchClick
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            SourcesTabRow(
                viewModel(),
                category = categoryID,
                onSourceClick = { sourceId ->
                    vm.getNews(sourceId)
                })
            NewsList(vm.newsList, vm.newsFoundState, vm.loadingState) {
                onNewsClick(it)
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsFragmentPreview() {
    NewsScreen(
        viewModel(),
        categoryID = "sports", categoryName = R.string.sports, rememberCoroutineScope(),
        rememberDrawerState(initialValue = DrawerValue.Closed), onNewsClick = {}, onSearchClick = {}
    )
}

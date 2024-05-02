package com.route.test.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.test.ui.theme.green
import com.route.test.utils.fragments.news.NewsViewModel

@Composable
fun SourcesTabRow(
    vm: NewsViewModel,
    category: String,
    onSourceClick: (sourceId: String) -> Unit
) {


    LaunchedEffect(key1 = Unit) {
        vm.getSources(category)
    }

    if (vm.sourcesList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            val sourceId = vm.sourcesList[0].id
            onSourceClick(sourceId ?: "")
        }
    }

    ScrollableTabRow(
        selectedTabIndex = vm.selectedTabIndex,
        indicator = {},
        divider = {},
        edgePadding = 8.dp,
    ) {
        vm.sourcesList.forEachIndexed { index, sourceItem ->
            Tab(
                selected = index == vm.selectedTabIndex,
                selectedContentColor = Color.White,
                unselectedContentColor = green,
                onClick = {
                    onSourceClick(sourceItem.id ?: "")
                    vm.selectedTabIndex = index
                },
            ) {
                Text(
                    text = sourceItem.name ?: "",
                    fontSize = 14.sp,
                    modifier = if (vm.selectedTabIndex == index) {
                        Modifier
                            .padding(8.dp)
                            .background(
                                color = green,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )


                    } else {
                        Modifier
                            .padding(8.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = green,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )
                    }
                )
            }

        }
    }

}


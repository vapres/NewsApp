package com.route.test.utils.fragments.newsdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.test.R
import com.route.test.activities.HomeActivity
import com.route.test.model.article.NewsItem
import com.route.test.ui.theme.greyDark
import com.route.test.ui.theme.Poppins
import com.route.test.widgets.NewsCard
import com.route.test.widgets.NewsTopAppBar

@Composable
fun NewsDetailsScreen(vm: NewsDetailsViewModel = viewModel(), title: String) {

    if (vm.newsItem == null)
        LaunchedEffect(Unit) {
            vm.getNewsItem(title)
        }


    Scaffold(
        topBar = {
            NewsTopAppBar(
                titleString = stringResource(R.string.news_title),
                shouldDisplaySearchIcon = false,
                shouldDisplayMenuIcon = false,
            )
        }
    ) { paddingValues ->
        vm.newsItem?.let { item ->
            NewsDetailsContent(paddingValues, item)
        }

    }

}

@Composable
fun NewsDetailsContent(paddingValues: PaddingValues, newsItem: NewsItem) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .verticalScroll(rememberScrollState())
    ) {
        NewsCard(newsItem)
        NewsDetailsCard(newsItem)
    }
}

@Composable
fun NewsDetailsCard(newsItem: NewsItem) {
    val context = (LocalContext.current) as HomeActivity
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Text(
            text = newsItem.content ?: "",
            modifier = Modifier.padding(10.dp)
        )
        Spacer(Modifier.fillMaxHeight(.7f))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.fillMaxWidth(.6f))
            Text(
                text = stringResource(R.string.view_full_article),
                fontFamily = Poppins,
                fontSize = 14.sp,
                color = greyDark,
                fontWeight = FontWeight.W300,
                modifier = Modifier.clickable { context.openWebsiteForNews(newsItem.url ?: "") }
            )
            Image(
                painter = painterResource(R.drawable.forward_arrow),
                contentDescription = null
            )
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsDetailsFragmentPreview() {
    NewsDetailsScreen(viewModel(), "News")
}
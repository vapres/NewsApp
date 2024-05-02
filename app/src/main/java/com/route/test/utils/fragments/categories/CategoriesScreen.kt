package com.route.test.utils.fragments.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.test.model.Category
import com.route.test.R
import com.route.test.ui.theme.Exo
import com.route.test.ui.theme.grey
import com.route.test.widgets.NewsTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoriesFragment(
    vm: CategoriesViewModel = viewModel(),
    scope: CoroutineScope,
    drawerState: DrawerState,
    onCategoryClick: (String, Int) -> Unit
) {
    Scaffold(
        topBar =
        {
            NewsTopAppBar(
                R.string.app_name,
                shouldDisplayMenuIcon = true,
                shouldDisplaySearchIcon = false,
                onSideMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )

        }
    ) { paddingValues: PaddingValues ->
        CategoriesFragmentContent(vm,paddingValues, onCategoryClick)
    }


}

@Composable
fun CategoriesFragmentContent(
    vm:CategoriesViewModel,
    paddingValues: PaddingValues,
    onCategoryClick: (String, Int) -> Unit
) {
    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
                .padding(top = paddingValues.calculateTopPadding())

        ) {
            Text(
                text = stringResource(R.string.pick_your_category_of_interest),
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = grey,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, bottom = 16.dp, end = 24.dp)
            )

            CategoriesList(vm){ id, name ->
                onCategoryClick(id, name)
            }
        }
    }
}


@Composable
fun CategoriesList(vm:CategoriesViewModel, onCategoryClick: (String, Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        items(vm.categories.size) { index ->
            CategoryCard(
                vm.categories[index],
                index,
                onCategoryClick = { id, name -> onCategoryClick(id, name) }
            )
        }
    }
}


@Composable
fun CategoryCard(category: Category, index: Int, onCategoryClick: (String, Int) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(category.backgroundColor),
            contentColor = Color.White
        ),
        shape =
        if (index % 2 == 0)
            RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp, bottomStart = 24.dp)
        else RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp, bottomEnd = 24.dp),

        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .size(width = 148.dp, height = 171.dp),

        onClick = {
            onCategoryClick(category.apiId, category.titleResId)
        }
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = stringResource(category.titleResId),
            modifier = Modifier
                .fillMaxWidth(.8F)
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(category.titleResId),
            fontFamily = Exo,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally)

        )
    }

}



@Preview(showBackground = true)
@Composable
private fun PreviewCategoriesList() {
    CategoriesList(viewModel()) { id, name -> }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCategoriesFragment() {
    CategoriesFragment(
        viewModel(),
        rememberCoroutineScope(),
        rememberDrawerState(initialValue = DrawerValue.Closed)
    ) { id, name -> }
}
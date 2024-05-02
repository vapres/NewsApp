package com.route.test.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.test.R
import com.route.test.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    titleResId: Int? = null,
    titleString: String = "",
    shouldDisplaySearchIcon: Boolean,
    shouldDisplayMenuIcon: Boolean,
    onSideMenuClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomEnd = 30.dp,
                    bottomStart = 30.dp
                )

            ),

        navigationIcon = {
            if (shouldDisplayMenuIcon) {
                AppBarIconButton(R.drawable.ic_menu, R.string.menu_icon){
                    if(onSideMenuClick != null)
                        onSideMenuClick()
                }
            } else
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(25.dp)
                )
        },

        title = {
            Text(
                text =
                if (titleResId != null)
                    stringResource(titleResId)
                else
                    titleString ?: "",

                style = MaterialTheme.typography.headlineSmall,
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = green,
            titleContentColor = Color.White
        ),

        actions = {
            if (shouldDisplaySearchIcon)
                AppBarIconButton(
                    R.drawable.ic_search,
                    R.string.search_icon
                ) {
                    if (onSearchClick != null)
                        onSearchClick()
                }
            else
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(25.dp)
                )
        }
    )
}

@Composable
fun AppBarIconButton(icon: Int, contentDescription: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(icon),
        contentDescription = stringResource(contentDescription),
        modifier = Modifier
            .padding(10.dp)
            .size(25.dp)
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsTopAppBarPreview() {
    NewsTopAppBar(R.string.app_name, shouldDisplayMenuIcon = true, shouldDisplaySearchIcon = true) {

    }
}
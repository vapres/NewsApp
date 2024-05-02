package com.route.test.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.test.R
import com.route.test.ui.theme.black
import com.route.test.ui.theme.green

@Composable
fun NavigationDrawerSheet(onCategoriesClick: () -> Unit, onSettingsClick: () -> Unit) {
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.8F)

    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = green)
                    .fillMaxWidth()
                    .fillMaxHeight(.2F)

            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,

                    )
            }
            NavigationDrawerSheetItems(
                R.drawable.ic_categories,
                R.string.categories,
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        start = 16.dp,
                        bottom = 8.dp
                    ),
                onNavigationItemClick = {
                    onCategoriesClick()
                }
            )

            NavigationDrawerSheetItems(
                R.drawable.ic_settings,
                R.string.settings,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 8.dp
                    ),
                onNavigationItemClick = {
                    onSettingsClick()
                }
            )
        }
    }
}

@Composable
fun NavigationDrawerSheetItems(
    iconResId: Int,
    textResId: Int,
    modifier: Modifier,
    onNavigationItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            onNavigationItemClick()
        }
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(R.string.navigation_item)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = textResId),
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 24.sp,
            color = black
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavigationDrawerSheetPreview() {
    NavigationDrawerSheet(onCategoriesClick = {}, onSettingsClick = {})
}
package com.route.test.utils.fragments.settings


import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.test.R
import com.route.test.activities.HomeActivity
import com.route.test.ui.theme.Poppins
import com.route.test.ui.theme.black
import com.route.test.ui.theme.green
import com.route.test.ui.theme.grey
import com.route.test.widgets.NewsTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SettingsScreen(vm: SettingsViewModel, scope: CoroutineScope, drawerState: DrawerState) {


    Scaffold(topBar = {
        NewsTopAppBar(
            shouldDisplaySearchIcon = false,
            shouldDisplayMenuIcon = true,
            titleResId = R.string.settings,
            onSearchClick = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }) { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .paint(
                    painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop
                )
                .padding(30.dp)
        ) {

            Text(
                text = stringResource(R.string.language),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = black
            )

            Spacer(Modifier.height(10.dp))
            LanguageDropDownMenu(vm)
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropDownMenu(viewModel: SettingsViewModel) {


    val activity = (LocalContext.current) as HomeActivity






    ExposedDropdownMenuBox(
        expanded = viewModel.isExpanded,
        onExpandedChange = { viewModel.isExpanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = viewModel.selectedLanguage, onValueChange = {

            }, readOnly = true,

            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = viewModel.isExpanded
                )
            }, colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = green,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = green,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ), modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = viewModel.isExpanded, onDismissRequest = { viewModel.isExpanded = false }) {

            DropdownMenuItem(text = {
                Text(text = stringResource(id = R.string.english))
            }, onClick = {
                viewModel.selectedLanguage = "English"
                viewModel.isExpanded = false
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
                activity.finish()
                activity.startActivity(activity.intent)
            }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

            DropdownMenuItem(text = {
                Text(text = stringResource(id = R.string.arabic))
            }, onClick = {
                viewModel.selectedLanguage = "العربية"
                viewModel.isExpanded = false
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ar"))
                activity.finish()
                activity.startActivity(activity.intent)

            }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    SettingsScreen(
        viewModel(),
        rememberCoroutineScope(),
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}
package com.example.gbook.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.gbook.R
import com.example.gbook.data.LayoutPreferencesRepository
import com.example.gbook.data.dataStore
import com.example.gbook.data.fake.FakeNetworkBooksRepository
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen
import com.example.gbook.ui.utils.Function

@Composable
fun AppHeaderBar(
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    onBack: () -> Unit,
    currentScreen: Screen,
    onIconClick: ((Screen) -> Unit),
    modifier: Modifier = Modifier,
    isConfiguration: Boolean = true,
) {
    if(uiState.currentBook == null) {
        when (currentScreen) {
            Screen.Home -> {
                HomeHeader(
                    account = uiState.account?.name?: stringResource(id = R.string.account),
                    onAccountClick = { onIconClick(Screen.Account) },
                    modifier = modifier
                )
            }
            Screen.Category -> {
                BookHeader(
                    currentScreen = currentScreen,
                    viewModel = viewModel,
                    title = uiState.currentBookCollection?.name
                        ?: stringResource(id = R.string.category),
                    onBack = onBack,
                    modifier = modifier
                )
            }
            else -> {
                BookHeader(
                    currentScreen = currentScreen,
                    viewModel = viewModel,
                    title = stringResource(id = currentScreen.title),
                    onBack = onBack,
                    modifier = modifier
                )
            }
        }
    } else {
        BookHeader(
            currentScreen = currentScreen,
            viewModel = viewModel,
            title = uiState.currentBook?.title?: stringResource(id = R.string.book),
            onBack = onBack,
            isConfiguration = isConfiguration,
            modifier = modifier
        )
    }
}
@Composable
fun BookHeader(
    title: String,
    currentScreen: Screen,
    viewModel: GBookViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    isConfiguration: Boolean = true,
) {
    val layoutPreferencesUiState = viewModel.layoutPreferencesUiState.collectAsState().value
    Row (
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .background(MaterialTheme.colorScheme.surface, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = title.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.headlineSmall
            )
        }
        if(
            currentScreen == Screen.Category && isConfiguration
            ) {
            HeaderButton(
                description = stringResource(layoutPreferencesUiState.toggleContentDescription),
                onClick = { viewModel.selectLayout(!layoutPreferencesUiState.isGridLayout) },
                painter = painterResource(layoutPreferencesUiState.toggleIcon),
                modifier = Modifier
            )
        }
    }
}
@Composable
fun DrawerBookHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
@Composable
fun OnlyAccountHomeHeader(
    account: String,
    onAccountClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
            .background(Color.Transparent),
    ) {
        HeaderButton(
            description = account,
            onClick = onAccountClick,
            imageVector = Icons.Default.AccountCircle,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
@Composable
fun HomeHeader(
    account: String,
    onAccountClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            HeaderButton(
                description = account,
                onClick = onAccountClick,
                imageVector = Icons.Default.AccountCircle,
            )
        }
    }
}

@Composable
fun DrawerHeader(
    text: String,
    account: String,
    onAccountClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = dimensionResource(id = R.dimen.elevation),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            HeaderButton(
                description = account,
                onClick = onAccountClick,
                imageVector = Icons.Default.AccountCircle,
            )
        }
    }
}
@Preview
@Composable
fun DrawerBookHeaderPreview() {
    DrawerBookHeader(title = MockData.bookUiState.currentBook!!.title)
}
@Preview
@Composable
fun BookHeaderPreview() {
    GBookTheme {
        BookHeader(
            currentScreen = Screen.Category,
            viewModel = GBookViewModel(
                FakeNetworkBooksRepository(),
                LayoutPreferencesRepository(LocalContext.current.dataStore)
            ),
            title = MockData.categoryUiState.currentBookCollection?.name!!,
            onBack = { }
        )
    }
}
@Preview
@Composable
fun HomeHeaderPreview() {
    GBookTheme {
        HomeHeader(
            account = stringResource(id = R.string.account),
            onAccountClick = {}
        )
    }
}
@Preview
@Composable
fun OnlyAccountHomeHeaderPreview() {
    GBookTheme {
        OnlyAccountHomeHeader(
            account = stringResource(id = R.string.account),
            onAccountClick = {}
        )
    }
}


@Preview
@Composable
fun DrawerHeaderPreview() {
    GBookTheme {
        DrawerHeader(
            text = stringResource(id = R.string.app_name),
            account = stringResource(id = R.string.account),
            onAccountClick = {}
        )
    }
}
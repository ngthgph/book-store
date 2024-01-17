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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen
import com.example.gbook.ui.utils.Function

@Composable
fun AppHeaderBar(
    currentScreen: Screen,
    uiState: GbookUiState,
    onIconClick: (Screen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                title = stringResource(id = uiState.currentCategory!!.name),
                onBack = onBack,
                modifier = modifier
            )
        }
        Screen.Book -> {
            BookHeader(
                currentScreen = currentScreen,
                title = uiState.currentBook!!.title,
                onBack = onBack,
                modifier = modifier
            )
        }
        else -> {
            BookHeader(
                currentScreen = currentScreen,
                title = currentScreen.name,
                onBack = onBack,
                modifier = modifier
            )
        }
    }
}
@Composable
fun BookHeader(
    title: String,
    currentScreen: Screen,
    onBack: () -> Unit,
    onButtonClick: (Function) -> Unit = {},
    modifier: Modifier = Modifier,
) {
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
            currentScreen == Screen.Favorite ||
            currentScreen == Screen.Category
            ) {
            Image(
                painter = painterResource(id = R.drawable.display_configuration),
                contentDescription = stringResource(R.string.display_configuration),
                modifier = Modifier
                    .clickable(onClick = { onButtonClick(Function.Configuration)})
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
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
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
            .background(Color.Transparent),
    ) {
        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = account,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .clickable(onClick = onAccountClick)
                .align(Alignment.CenterEnd)
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
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = account,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable (onClick = onAccountClick)
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
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = account,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable (onClick = onAccountClick)
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
    BookHeader(
        currentScreen = Screen.Category,
        title = stringResource(id = MockData.categoryUiState.currentCategory!!.name),
        onBack = { }
    )
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
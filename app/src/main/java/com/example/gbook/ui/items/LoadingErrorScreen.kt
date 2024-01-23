package com.example.gbook.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}
@Composable
fun ErrorContent(
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "",
            modifier = Modifier
                .sizeIn(maxWidth = dimensionResource(id = R.dimen.image_size_large))
                .aspectRatio(1f)
        )
        Text(
            text = stringResource(R.string.fail_to_load),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
        DescriptionButton(
            function = Function.Retry,
            onButtonClick = onButtonClick
        )
    }
}
@Preview
@Composable
fun LoadingPreview() {
    GBookTheme {
        LoadingContent()
    }
}
@Preview
@Composable
fun ErrorPreview() {
    GBookTheme {
        ErrorContent(onButtonClick = {})
    }
}
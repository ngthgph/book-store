package com.example.gbook.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.theme.GBookTheme

@Composable
fun AccountScreen(
    uiState: GbookUiState,
    modifier: Modifier = Modifier
) {
    AccountContent()
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun CompactAccountScreenPreview() {
    GBookTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumAccountScreenPreview() {
    GBookTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedAccountScreenPreview() {
    GBookTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}
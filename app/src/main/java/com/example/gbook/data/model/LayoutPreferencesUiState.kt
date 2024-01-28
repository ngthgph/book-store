package com.example.gbook.data.model

import com.example.gbook.R

data class LayoutPreferencesUiState(
    val isGridLayout: Boolean = true,
    val toggleContentDescription: Int =
        if (isGridLayout) R.string.grid_layout else R.string.linear_layout,
    val toggleIcon: Int =
        if (!isGridLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
)

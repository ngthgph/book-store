package com.example.gbook.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Collection(
    @StringRes val name: Int,
    @DrawableRes val image: Int,
)

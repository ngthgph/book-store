package com.example.gbook.ui.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gbook.R

enum class Function(
    val icon: ImageVector = Icons.Default.Warning,
    val doneIcon: ImageVector = Icons.Default.Done,
    @StringRes val description: Int
) {
    Library(
        icon = Icons.Default.Add,
        description = R.string.add_to_library
    ),
    Cart(
        icon = Icons.Default.ShoppingCart,
        description = R.string.add_to_cart
    ),
    Share(
        icon = Icons.Default.Share,
        description = R.string.share
    ),
    Cancel(
        description = R.string.cancel
    ),
    Add (
        icon = Icons.Default.KeyboardArrowUp,
        description = R.string.add
    ),
    Decline (
        icon = Icons.Default.KeyboardArrowDown,
        description = R.string.decline
    ),
    Delete(
        icon = Icons.Default.Delete,
        description = R.string.delete
    ),
    Checkout(
        description = R.string.checkout
    ),
    AddToCart(
        icon = Icons.Default.Add,
        description = R.string.add_to_cart
    ),
    Configuration(
        description = R.string.display_configuration
    ),
    SignIn(
        description = R.string.sign_in
    ),
    ForgetPassword(
        description = R.string.forget_password
    ),
    SignUp(
        description = R.string.sign_up
    ),
    PreviousPage(
        description = R.string.previous_page
    ),
    NextPage(
        description = R.string.next_page
    )
}
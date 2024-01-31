package com.example.gbook.ui

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import com.example.gbook.R
import com.example.gbook.data.database.books.Book

fun Context.shareBook(book: Book) {

//    val imageUri = toImageUri(context, book.imageLinks)
    val text = toPlainText(book)

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, book.title)
//        putExtra(Intent.EXTRA_STREAM, imageUri)
        putExtra(Intent.EXTRA_TEXT, text)
//        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    this.startActivity(Intent.createChooser(intent,book.title))
}

private fun toPlainText(book: Book): String {
    val builder = StringBuilder()
    builder.append("${book.title}\n")
    builder.append("Author: ${book.author}\n")
    builder.append("Publisher: ${book.publisher}\n")
    builder.append("Published Date: ${book.publishedDate}\n")
    builder.append("Content: ${book.description}\n")
    builder.append("ISBN-13: ${book.isbn13}\n")
    builder.append("ISBN-10: ${book.isbn10}\n")
    builder.append("Page: ${book.pageCount}\n")
    builder.append("Categories: ${book.categories}\n")
    builder.append("Saleability: ${book.saleability}\n")
    builder.append("Retail Price: ${book.retailPrice} ${book.currencyCode}\n")
    return builder.toString()
}

private fun toImageUri(context: Context, imageLinks: String): Uri {
    // Use broken image in case cannot get image uri
    val brokenImageUri = context.getImageUri(R.drawable.ic_broken_image)

    // Create imageUri from image link
    val imageUri = Uri.parse(imageLinks)

    return imageUri?: brokenImageUri
}

private fun Context.getImageUri(@DrawableRes image: Int): Uri {
    val resources = this.resources

    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(resources.getResourcePackageName(image))
        .appendPath(resources.getResourceTypeName(image))
        .appendPath(resources.getResourceEntryName(image))
        .build()
}
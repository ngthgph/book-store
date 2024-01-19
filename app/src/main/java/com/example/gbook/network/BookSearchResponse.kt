package com.example.gbook.network

import kotlinx.serialization.Serializable

@Serializable
data class BookSearchResponse (
    val items: List<BookItem>?
)
@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: Pair<Isbn10, Isbn13>?,
    val printedPageCount: Int?,
    val categories: List<String>?,
    val imageLinks: ImageLinks?,
    val saleInfo: SaleInfo?,
)
@Serializable
data class VolumeInfo(
    val title: String,
    val author: List<String>,
)
@Serializable
data class Isbn10(
    val identifier: String,
)
@Serializable
data class Isbn13(
    val identifier: String,
)
@Serializable
data class ImageLinks(
    val thumbnail: String
)
@Serializable
data class SaleInfo(
    val saleability: String,
    val retailPrice: RetailPrice?
)
@Serializable
data class RetailPrice(
    val amount: Double,
    val currencyCode: String,
)
package com.example.gbook.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookSearchResponse (
    val items: List<BookItem>? = null
)
@Serializable
data class BookItem(
    @SerialName(value = "id")
    val networkId: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
)
@Serializable
data class VolumeInfo(
    val title: String,
    @SerialName(value = "authors")
    val author: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val industryIdentifiers: List<Isbn>? = null,
    val printedPageCount: Int? = null,
    val categories: List<String>? = null,
    val imageLinks: ImageLinks? = null,
)
@Serializable
data class Isbn(
    val identifier: String,
)
@Serializable
data class ImageLinks(
    val thumbnail: String
)
@Serializable
data class SaleInfo(
    val saleability: String,
    val retailPrice: RetailPrice? = null
)
@Serializable
data class RetailPrice(
    val amount: Double,
    val currencyCode: String,
)
package com.example.gbook.network

import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
)
@Serializable
data class VolumeInfo(
    val title: String,
    val author: Array<String>,
    val description: String,
    val pageCount: String,
    val imageLinks: ImageLinks
)
@Serializable
data class ImageLinks(
    val thumbnail: String
)
@Serializable
data class SaleInfo(
    val retailPrice: RetailPrice
)
@Serializable
data class RetailPrice(
    val amount: String,
    val currencyCode: String,
)
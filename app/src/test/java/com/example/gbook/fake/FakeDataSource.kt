package com.example.gbook.fake

import com.example.gbook.data.model.Book
import com.example.gbook.network.BookItem
import com.example.gbook.network.BookSearchResponse
import com.example.gbook.network.ImageLinks
import com.example.gbook.network.Isbn10
import com.example.gbook.network.Isbn13
import com.example.gbook.network.RetailPrice
import com.example.gbook.network.SaleInfo
import com.example.gbook.network.VolumeInfo

object FakeDataSource {
    val fakeBookItem1 = BookItem(
        id = "123456789",
        volumeInfo = VolumeInfo(
            title = "Fake Book 1",
            author = listOf("Author A", "Author B"),
            publisher = "Fake Publisher 1",
            publishedDate = "2022-01-15",
            description = "This is a fake book.",
            industryIdentifiers = Pair(Isbn10("123456789X"), Isbn13("9781234567890")),
            printedPageCount = 250,
            categories = listOf("Fiction", "Mystery"),
            imageLinks = ImageLinks("https://example.com/thumbnail1.jpg")
        ),
        saleInfo = SaleInfo(
            saleability = "FOR_SALE",
            retailPrice = RetailPrice(amount = 29.99, currencyCode = "USD")
        )
    )
    val fakeBookItem2 = BookItem(
        id = "2123456789",
        volumeInfo = VolumeInfo(
            title = "Fake Book 2",
            author = listOf("Author C", "Author D"),
            publisher = "Fake Publisher 2",
            publishedDate = "2022-02-20",
            description = "Another fake book.",
            industryIdentifiers = Pair(Isbn10("9876543210"), Isbn13("9789876543210")),
            printedPageCount = 180,
            categories = listOf("Science Fiction", "Adventure"),
            imageLinks = ImageLinks("https://example.com/thumbnail2.jpg")
        ),
        saleInfo = SaleInfo(
            saleability = "FOR_SALE",
            retailPrice = RetailPrice(amount = 24.99, currencyCode = "USD")
        )
    )
    val fakeBookResponse = BookSearchResponse(
        items = listOf(fakeBookItem1, fakeBookItem2)
    )

    private val fakeBook1 = Book(
        id = 0,
        title = "Fake Book 1",
        author = "Author A, Author B",
        publisher = "Fake Publisher 1",
        publishedDate = "2022-01-15",
        description = "This is a fake book.",
        isbn13 = "9781234567890",
        isbn10 = "123456789X",
        pageCount = 250,
        categories = "Fiction, Mystery",
        imageLinks = "https://example.com/thumbnail1.jpg",
        saleability = "FOR_SALE",
        retailPrice = 29.99,
        currencyCode = "USD",
        rating = null,
        note = "",
        favorite = false
    )
    private val fakeBook2 = Book(
        id = 1,
        title = "Fake Book 2",
        author = "Author C, Author D",
        publisher = "Fake Publisher 2",
        publishedDate = "2022-02-20",
        description = "Another fake book.",
        isbn13 = "9789876543210",
        isbn10 = "9876543210",
        pageCount = 180,
        categories = "Science Fiction, Adventure",
        imageLinks = "https://example.com/thumbnail2.jpg",
        saleability = "FOR_SALE",
        retailPrice = 24.99,
        currencyCode = "USD",
        rating = null,
        note = "",
        favorite = false
    )
    val fakeBookList = listOf(fakeBook1, fakeBook2)

    const val json = """
{
  "items": [
    {
      "id": "123456789",
      "volumeInfo": {
        "title": "Fake Book 1",
        "authors": ["Author A", "Author B"],
        "publisher": "Fake Publisher 1",
        "publishedDate": "2022-01-15",
        "description": "This is a fake book.",
        "industryIdentifiers": {
          "identifier": "123456789X"
        },
        "printedPageCount": 250,
        "categories": ["Fiction", "Mystery"],
        "imageLinks": {
          "thumbnail": "https://example.com/thumbnail1.jpg"
        }
      },
      "saleInfo": {
        "saleability": "FOR_SALE",
        "retailPrice": {
          "amount": 29.99,
          "currencyCode": "USD"
        }
      }
    },
    {
      "id": "2123456789",
      "volumeInfo": {
        "title": "Fake Book 2",
        "authors": ["Author C", "Author D"],
        "publisher": "Fake Publisher 2",
        "publishedDate": "2022-02-20",
        "description": "Another fake book.",
        "industryIdentifiers": {
          "identifier": "9876543210"
        },
        "printedPageCount": 180,
        "categories": ["Science Fiction", "Adventure"],
        "imageLinks": {
          "thumbnail": "https://example.com/thumbnail2.jpg"
        }
      },
      "saleInfo": {
        "saleability": "FOR_SALE",
        "retailPrice": {
          "amount": 24.99,
          "currencyCode": "USD"
        }
      }
    }
  ]
}
"""
}
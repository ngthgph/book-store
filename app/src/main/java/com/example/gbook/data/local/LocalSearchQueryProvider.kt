package com.example.gbook.data.local

import com.example.gbook.data.network.BookFilter
import com.example.gbook.data.network.OrderBy
import com.example.gbook.data.database.books.SearchQuery
import java.util.Locale

object LocalSearchQueryProvider {
    val recommendedQuery = SearchQuery(
        query = "search term",
        maxResults = 40,
        filter = BookFilter.PAID_EBOOKS,
        orderBy = OrderBy.NEWEST
    )
    val String.categoryQuery: SearchQuery
        get() = SearchQuery(
            query = "",
            subject = this.lowercase(Locale.getDefault()),
            maxResults = 40,
            filter = BookFilter.PAID_EBOOKS,
            orderBy = OrderBy.NEWEST)
}
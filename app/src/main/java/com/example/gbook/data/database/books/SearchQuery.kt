package com.example.gbook.data.database.books

import com.example.gbook.data.network.BookFilter
import com.example.gbook.data.network.OrderBy
import com.example.gbook.data.network.PrintType
import com.example.gbook.data.network.Projection

data class SearchQuery(
    var query: String,
    var intitle: String? = null,
    var inauthor: String? = null,
    var inpublisher: String? = null,
    var subject: String? = null,
    var isbn: String? = null,
    var lccn: String? = null,
    var oclc: String? = null,
    var filter: BookFilter? = null,
    var startIndex: Int? = null,
    var maxResults: Int? = null,
    var printType: PrintType? = null,
    var projection: Projection? = null,
    var orderBy: OrderBy? = null,
)

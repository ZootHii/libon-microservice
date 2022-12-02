package com.libon.libraryservice.dto

data class LibraryDto @JvmOverloads constructor(
        val libraryId: String? = "",
        val userBookList: List<BookDto>? = ArrayList()
)
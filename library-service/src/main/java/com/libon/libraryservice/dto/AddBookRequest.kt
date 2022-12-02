package com.libon.libraryservice.dto

data class AddBookRequest constructor(
        val libraryId: String,
        val isbn: String
)
package com.example.domain.model

data class Author(
        val id: String,
        val name: String
) {
    var books: List<Book>? = null

}

data class AuthorRequest(
        val name: String
)

data class Book(
        val id: String,
        val name: String,
        val authorId: String
) {
    var author: Author? = null
}

data class BookRequest(
        val name: String,
        val authoerId: String
)
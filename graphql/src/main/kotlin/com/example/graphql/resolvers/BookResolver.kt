package com.example.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.domain.model.Book
import com.example.domain.model.BookRequest
import com.example.domain.service.BookService
import org.springframework.stereotype.Component

@Component
class BookQueryResolver(val bookService: BookService) : GraphQLQueryResolver {

    fun listBook(): List<Book> {
        return bookService.list()
    }

    fun getBookById(id: String): Book? {
        return bookService.get(id)
    }
}

@Component
class BookQueryMutationResolver(val bookService: BookService) : GraphQLMutationResolver {

    fun registBook(name: String, authorId: String): Book {
        val request = BookRequest(name, authorId)
        return bookService.create(request)
    }
}
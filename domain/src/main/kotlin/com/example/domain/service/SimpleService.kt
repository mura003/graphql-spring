package com.example.domain.service

import com.example.domain.model.Author
import com.example.domain.model.AuthorRequest
import com.example.domain.model.Book
import com.example.domain.model.BookRequest
import com.example.domain.repository.AuthorRepository
import com.example.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(val repository: AuthorRepository) {

    fun create(authorRequest: AuthorRequest): Author {
        return repository.create(authorRequest)
    }

    fun get(id: String): Author? {
        return repository.get(id)
    }

    fun list(): List<Author> {
        return repository.list()
    }

    fun delete(id: String) {
        repository.delete(id)
    }
}

@Service
class BookService(val repository: BookRepository, val authorRepository: AuthorRepository) {
    fun create(bookRequest: BookRequest): Book {
        return repository.create(bookRequest)
    }

    fun get(id: String): Book? {
        val book = repository.get(id)

        if (book == null) {
            return book
        }

        book.author = authorRepository.get(book.authorId)
        return book
    }

    fun list(): List<Book> {
        val books = repository.list()
        val authors = authorRepository.findList(books.map { it.authorId })

        books.forEach {
            if (authors.containsKey(it.authorId)) {
                it.author = authors[it.authorId]
            }
        }

        return books
    }

    fun delete(id: String) {
        repository.delete(id)
    }

    fun findAuthorId(authorId: String): List<Book> {
        return repository.findList(authorId)
    }
}
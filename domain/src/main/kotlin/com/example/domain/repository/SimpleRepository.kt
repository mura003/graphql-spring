package com.example.domain.repository

import com.example.domain.model.Author
import com.example.domain.model.AuthorRequest
import com.example.domain.model.Book
import com.example.domain.model.BookRequest
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AuthorRepository(private val bookRepository: BookRepository) {

    val repository = mutableMapOf<String, Author>()

    fun create(authorRequest: AuthorRequest): Author {
        val id = UUID.randomUUID().toString()
        val author = Author(id, authorRequest.name)

        repository[id] = author
        return author
    }

    fun list(): List<Author> {
        val authors = repository.map { it.value }

        val authorBookList = bookRepository.findAuthorBooks(authors.map { it.id })

        authors.forEach {
            it.books = authorBookList[it.id]
        }

        return authors
    }

    fun findList(ids: List<String>): Map<String, Author> {
        return repository.filter {
            ids.contains(it.key)
        }
    }

    fun get(id: String): Author? {
        val author = repository[id]

        if (author == null) {
            return null
        }

        author.books = bookRepository.findList(author.id)

        return author
    }

    fun delete(id: String) {
        repository.remove(id)
    }
}

@Repository
class BookRepository() {
    val repository = mutableMapOf<String, Book>()

    fun create(bookRequest: BookRequest): Book {
        val id = UUID.randomUUID().toString()
        val book = Book(id, bookRequest.name, bookRequest.authoerId)

        repository[id] = book
        return book
    }

    fun list(): List<Book> {
        return repository.map { it.value }
    }

    fun get(id: String): Book? {
        return repository[id]
    }

    fun delete(id: String) {
        repository.remove(id)
    }

    fun findList(authorId: String): List<Book> {
        return repository.filter { it.value.authorId.equals(authorId) }.map { it.value }
    }

    fun findAuthorBooks(authorIds: List<String>): Map<String, List<Book>> {
        val authorBookList = mutableMapOf<String, MutableList<Book>>()

        repository.filter {
            authorIds.contains(it.value.authorId)
        }.map {
            var list = authorBookList[it.value.authorId]
            if (list == null) {
                list = mutableListOf<Book>()
                authorBookList[it.value.authorId] = list
            }
            list.add(it.value)
        }

        return authorBookList

    }
}
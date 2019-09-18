package com.example.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.domain.model.Author
import com.example.domain.model.AuthorRequest
import com.example.domain.service.AuthorService
import org.springframework.stereotype.Component


@Component
class AuthorQueryResolver(val authorService : AuthorService) : GraphQLQueryResolver {

    fun getAuthorById(authorId: String) : Author? {
        return authorService.get(authorId)
    }

    fun listAuthor(): List<Author> {
        return authorService.list()
    }
}
@Component
class AuthorMutationResolver(val authorService: AuthorService) : GraphQLMutationResolver {

    fun registAuthor(name: String): Author? {
        val request = AuthorRequest(name)
        return authorService.create(request)
    }
}
package com.example.graphql.model

data class RegistAuthorResponse(
        val name: String,
        val errorMessage: String,
        val regist: Boolean
)

data class RegistBookResponse(
        val authorId: String,
        val name: String,
        val errorMessage: String,
        val regist: Boolean
)
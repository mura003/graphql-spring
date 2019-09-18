package com.example.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver

class QueryResolver : GraphQLQueryResolver {
    fun version(): String {
        return "0.0.1"
    }
}
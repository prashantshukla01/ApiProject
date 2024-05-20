package com.example.apiproject

data class DummyJasonProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
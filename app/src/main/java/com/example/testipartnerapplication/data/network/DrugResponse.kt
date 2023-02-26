package com.example.testipartnerapplication.data.network

data class DrugResponseItem(
    val description: String,
    val documentation: String,
    val fields: List<Field>,
    val id: Int,
    val image: String,
    val name: String
)
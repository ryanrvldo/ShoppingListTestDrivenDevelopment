package com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.responses

data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)
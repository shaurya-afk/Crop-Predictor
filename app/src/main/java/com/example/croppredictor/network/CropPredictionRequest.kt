package com.example.croppredictor.network

data class CropPredictionRequest(
    val N: Int,
    val P: Int,
    val K: Int,
    val temperature: Double,
    val humidity: Int,
    val ph: Double,
    val rainfall: Int
)
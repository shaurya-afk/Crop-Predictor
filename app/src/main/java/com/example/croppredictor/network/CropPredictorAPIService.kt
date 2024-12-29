package com.example.croppredictor.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.29.43:5000/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface CropPredictorAPIService{
    @POST("predict")
    suspend fun getPrediction(@Body request:
    CropPredictionRequest):CropPredictionResponse
}

object CropPredictorAPI{
    val retrofitService:CropPredictorAPIService by lazy {
        retrofit.create(CropPredictorAPIService::class.java)
    }

}
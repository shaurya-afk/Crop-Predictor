package com.example.croppredictor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.croppredictor.network.CropPredictionRequest
import com.example.croppredictor.network.CropPredictorAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CropPredictorViewModel: ViewModel() {
    private val _predictionResult = MutableStateFlow<String>("")
    val predictionResult:StateFlow<String> = _predictionResult

    fun fetchPrediction(request: CropPredictionRequest) {
        viewModelScope.launch {
            try {
                val result = CropPredictorAPI.retrofitService.getPrediction(request)
                println("API Response: $result")
                _predictionResult.value = result.class_name?:"No Crop Predicted!!"
            } catch (e: Exception) {
                _predictionResult.value = "Error: ${e.message}"
                println("API Error: ${e.message}")
            }
        }
    }
}
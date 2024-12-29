package com.example.croppredictor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.croppredictor.network.CropPredictionRequest
import com.example.croppredictor.ui.theme.DarkGreen
import com.example.croppredictor.ui.theme.LightGreen

@Composable
fun CropPredictor(viewModel: CropPredictorViewModel = viewModel()) {
    val labels = listOf(
        "Nitrogen (kg/ha)",
        "Phosphorus (kg/ha)",
        "Potassium (kg/ha)",
        "Temperature (°C)",
        "Humidity (%)",
        "Ph (0-14)",
        "Rainfall (mm)"
    )

    val values = remember {
        mutableStateListOf("","","","","","","")
    }

    val predictionResult = viewModel.predictionResult.collectAsState().value
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Text(
            text = "Crop Predictor",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top=56.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(7){
                    index ->
                CropPredictorInputField(
                    label = labels[index],
                    value = values[index],
                    onValueChange = {
                            newValue ->
                        values[index] = newValue
                    }
                )
            }
        }
        var showDialog by remember {
            mutableStateOf(false)
        }

        val context = LocalContext.current

        ElevatedButton(onClick = {
            if (values.any { it.isBlank() }) {
                Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show()
                return@ElevatedButton
            }
            val request = CropPredictionRequest(
                N = values[0].toInt(),        // Nitrogen (N)
                P = values[1].toInt(),        // Phosphorus (P)
                K = values[2].toInt(),        // Potassium (K)
                temperature = values[3].toDouble(), // Temperature
                humidity = values[4].toInt(), // Humidity
                ph = values[5].toDouble(),    // pH
                rainfall = values[6].toInt(), // Rainfall
            )
            println("Sending Request: $request")
            viewModel.fetchPrediction(request)

            showDialog = true
        },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = DarkGreen,
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Predict",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

        }
        ElevatedButton(onClick = {
            values.fill("")
        },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(92.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Reset",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if(showDialog && predictionResult.isNotBlank()){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Prediction Result") },
                text = { Text("Predicted Crop: $predictionResult") },
                confirmButton = {
                    Button(onClick = {showDialog=false}) {
                        Text("OK")
                    }
                }
            )
        }else if(showDialog && predictionResult.isBlank()){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Server Error!") },
                text = { Text("Server not found") },
                confirmButton = {
                    Button(onClick = {showDialog=false}) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewCropPredictor(){
    CropPredictor()
}
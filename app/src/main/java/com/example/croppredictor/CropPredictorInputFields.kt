package com.example.croppredictor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.croppredictor.ui.theme.DarkGreen

@Composable
fun CropPredictorInputField(label:String, value:String, onValueChange:(String)->Unit){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}

@Composable
@Preview
fun PreviewCropPredictorInputField(){
    val textValue = remember {
        mutableStateOf("")
    }
    CropPredictorInputField(
        label = "Nitrogen",
        value = textValue.value,
        onValueChange = {textValue.value = it}
    )
}
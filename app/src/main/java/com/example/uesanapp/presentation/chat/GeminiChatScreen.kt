package com.example.uesanapp.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GeminiChatScreen(apiKey: String
                     , viewModel: GeminiViewModel = viewModel()){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        OutlinedTextField(
            value = viewModel.propmt,
            onValueChange = {viewModel.propmt = it},
            label = {Text("Haz tu pregunta")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {viewModel.askGemini(apiKey)},
            enabled = !viewModel.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Preguntar")
        }
        if(viewModel.isLoading)
            CircularProgressIndicator()
        else {
            Text(
                text = viewModel.response,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }


}
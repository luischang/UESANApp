package com.example.uesanapp.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uesanapp.data.model.GeminiInteractionRequest
import com.example.uesanapp.data.remote.gemini.GeminiApiService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GeminiViewModel : ViewModel(){

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(GeminiApiService::class.java)
    var propmt by mutableStateOf("")
    var response by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun askGemini(apiKey: String){
        viewModelScope.launch {
            try {
                isLoading = true
                val request = GeminiInteractionRequest(
                    model = "gemini-2.5-flash",
                    input = propmt
                )
                val result = apiService.generateContent(apiKey, request)
                val modelOutput = result.steps.find{it.type == "model-output"}
                response = modelOutput?.content?.firstOrNull()?.text ?: "No response"
                isLoading = false

            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                response = "Error: $errorBody"
            }catch (ex: Exception){
                response = "Error: ${ex.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
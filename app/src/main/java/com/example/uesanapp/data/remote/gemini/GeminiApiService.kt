package com.example.uesanapp.data.remote.gemini

import com.example.uesanapp.data.model.GeminiInteractionRequest
import com.example.uesanapp.data.model.GeminiInteractionResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GeminiApiService {

    @POST("v1beta/interactions")
    suspend fun generateContent(
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiInteractionRequest
    ): GeminiInteractionResponse
}
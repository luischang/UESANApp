package com.example.uesanapp.data.model
data class GeminiInteractionRequest(
    val model: String,
    val input: String
)
data class GeminiInteractionResponse(
    val id: String,
    val status: String,
    val model: String,
    val steps: List<InteractionStep>
)
data class InteractionStep(
    val type: String,
    val signature: String? = null,
    val content: List<InteractionContent>? = null,
)
data class InteractionContent(
    val text: String,
    val type: String
)

package model

data class Schema(
    val type: Type,
    val title: String,
    val properties: Map<String, Property>? = null,
    val enum: Set<String>? = null
)

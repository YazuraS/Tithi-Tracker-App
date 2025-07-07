package com.example.hinducalenderreminder

import com.google.gson.annotations.SerializedName

data class PanchangResponse(
    val message: String,
    @SerializedName("simplified_file")
    val simplifiedFile: String,
    val data: Map<String, List<PanchangEntry>>
)

data class PanchangEntry(
    val date: String,
    val tithi: String,
    val sunrise: String?,
    val sunset: String?,
    val moonrise: String?,
    val moonset: String?
)


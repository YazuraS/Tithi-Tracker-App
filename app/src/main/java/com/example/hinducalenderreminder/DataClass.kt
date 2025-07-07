package com.example.hinducalenderreminder

import com.google.gson.annotations.SerializedName

data class PanchangResponse(
    val message: String,
    @SerializedName("simplified_file")
    val simplifiedFile: String,
    val data: Map<String, List<PanchangEntry>>
)

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("expires_in")
    val expiresIn: Int
)

//data class PanchangData(
//    val vaara: String,
//    val nakshatra: List<Nakshatra>,
//    val tithi: List<Tithi>,
//    val karana: List<Karana>,
//    val yoga: List<Yoga>,
//    val sunrise: String,
//    val sunset: String,
//    val moonrise: String,
//    val moonset: String
//)


data class Nakshatra(
    val id: Int,
    val name: String,
    val lord: Lord,
    val start: String,
    val end: String
)

data class Tithi(
    val id: Int,
    val index: Int,
    val name: String,
    val paksha: String,
    val start: String,
    val end: String
)

data class Karana(
    val id: Int,
    val index: Int,
    val name: String,
    val start: String,
    val end: String
)

data class Yoga(
    val id: Int,
    val name: String,
    val start: String,
    val end: String
)

data class Lord(
    val id: Int,
    val name: String,
    val vedic_name: String
)

data class PanchangYearData(
    val year: Int,
    val entries: List<PanchangEntry>
)

//typealias YearlyPanchangMap = Map<String, List<PanchangEntry>> // Month name -> List of days

data class PanchangEntry(
    val date: String,
    val tithi: String,
    val sunrise: String?,
    val sunset: String?,
    val moonrise: String?,
    val moonset: String?
)


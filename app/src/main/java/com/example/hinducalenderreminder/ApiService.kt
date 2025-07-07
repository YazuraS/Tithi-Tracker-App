package com.example.hinducalenderreminder

import retrofit2.Response
import retrofit2.http.*

interface PanchangApiService {

    @FormUrlEncoded
    @POST("token")
    suspend fun getToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<TokenResponse>

//    @GET("v2/astrology/panchang")
//    suspend fun getPanchangForToday(
//        @Header("Authorization") bearerToken: String,
//        @Query("ayanamsa") ayanamsa: Int = 1,
//        @Query("coordinates") coordinates: String,
//        @Query("datetime") datetime: String,
//        @Query("la") language: String = "en"
//    ): Response<PanchangResponse>

//    @GET("/v1/calendars/coordinates/{lat}/{lon}/years/{year}")
//    suspend fun getYearlyPanchanga(
//        @Path("lat") latitude: String,
//        @Path("lon") longitude: String,
//        @Path("year") year: String,
//        @Query("timezone") timezone: String = "Asia/Calcutta"
//    ): Response<YearlyPanchangMap>


}

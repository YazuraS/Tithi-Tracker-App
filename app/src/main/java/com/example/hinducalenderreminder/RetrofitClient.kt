package com.example.hinducalenderreminder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //private const val BASE_URL = "https://api.prokerala.com/"

    private const val BASE_URL = "https://api.prokerala.com/"


    val instance: PanchangApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PanchangApiService::class.java)
    }

}

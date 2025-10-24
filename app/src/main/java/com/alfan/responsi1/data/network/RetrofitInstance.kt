package com.alfan.responsi1.data.network

import com.alfan.responsi1.utils.Constants // Ganti dengan package name Anda
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // 'by lazy' artinya objek Retrofit baru akan dibuat saat 'api' diakses pertama kali
    val api: ApiService by lazy {
        Retrofit.Builder()
            // 1. Mengambil BASE_URL dari file Constants.kt Anda
            .baseUrl(Constants.BASE_URL)
            // 2. Memberi tahu Retrofit untuk memakai Gson
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // 3. Membuat implementasi dari interface ApiService Anda
            .create(ApiService::class.java)
    }
}
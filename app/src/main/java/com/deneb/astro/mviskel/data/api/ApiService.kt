package com.deneb.astro.mviskel.data.api

import com.deneb.astro.mviskel.data.model.Response
import retrofit2.http.GET

interface ApiService {

    @GET("hero")
    suspend fun getHeroes(): Response
}
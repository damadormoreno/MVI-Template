package com.deneb.astro.mviskel.data.api

import com.deneb.astro.mviskel.data.model.Response
import retrofit2.Retrofit

class ApiServiceImpl(retrofit: Retrofit) : ApiService {

    private val apiService by lazy { retrofit.create(ApiService::class.java) }

    override suspend fun getHeroes(): Response {
        return apiService.getHeroes()
    }
}
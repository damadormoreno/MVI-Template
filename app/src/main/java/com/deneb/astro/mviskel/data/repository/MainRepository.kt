package com.deneb.astro.mviskel.data.repository

import com.deneb.astro.mviskel.data.api.ApiServiceImpl
import com.deneb.astro.mviskel.data.model.Hero

interface MainRepository {

    suspend fun getHeroes(): List<Hero>

    class MainRepositoryImpl(
        private val service: ApiServiceImpl
    ): MainRepository {

        override suspend fun getHeroes(): List<Hero> {
            return service.getHeroes().results
        }

    }

}

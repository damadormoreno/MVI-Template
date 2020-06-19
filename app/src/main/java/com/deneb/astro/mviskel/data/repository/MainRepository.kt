package com.deneb.astro.mviskel.data.repository

import com.deneb.astro.mviskel.data.api.ApiHelperImpl
import com.deneb.astro.mviskel.data.model.Hero

interface MainRepository {

    suspend fun getHeroes(): List<Hero>

    class MainRepositoryImpl(
        private val service: ApiHelperImpl
    ): MainRepository {

        override suspend fun getHeroes(): List<Hero> {
            return service.getHeroes().results
        }

    }

}

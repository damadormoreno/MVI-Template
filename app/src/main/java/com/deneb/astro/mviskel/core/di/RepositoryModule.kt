package com.deneb.astro.mviskel.core.di

import com.deneb.astro.mviskel.data.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MainRepository.MainRepositoryImpl> { MainRepository.MainRepositoryImpl(get()) }
    factory<MainRepository> { MainRepository.MainRepositoryImpl(get()) }
}
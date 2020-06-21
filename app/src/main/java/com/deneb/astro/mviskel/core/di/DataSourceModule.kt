package com.deneb.astro.mviskel.core.di

import com.deneb.astro.mviskel.data.api.ApiServiceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory { ApiServiceImpl(get()) }
}
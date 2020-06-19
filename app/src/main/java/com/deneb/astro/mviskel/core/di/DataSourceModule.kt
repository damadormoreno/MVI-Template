package com.deneb.astro.mviskel.core.di

import com.deneb.astro.mviskel.data.api.ApiHelperImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory { ApiHelperImpl(get()) }
}
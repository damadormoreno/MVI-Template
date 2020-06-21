package com.deneb.astro.mviskel.core.di

import com.deneb.astro.mviskel.ui.herolist.HeroListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HeroListViewModel(get())
    }
}
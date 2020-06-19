package com.deneb.astro.mviskel

import android.app.Application
import com.deneb.astro.mviskel.core.di.dataSourceModule
import com.deneb.astro.mviskel.core.di.networkModule
import com.deneb.astro.mviskel.core.di.repositoryModule
import com.deneb.astro.mviskel.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                dataSourceModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}
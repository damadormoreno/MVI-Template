package com.deneb.astro.mviskel.ui.main.viewstate

import com.deneb.astro.mviskel.data.model.Hero


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Heroes(val heroes: List<Hero>) : MainState()
    data class Error(val error: String?) : MainState()

}
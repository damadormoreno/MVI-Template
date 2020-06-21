package com.deneb.astro.mviskel.ui.herolist

import com.deneb.astro.mviskel.data.model.Hero


sealed class HeroListState {
    object Idle : HeroListState()
    object Loading : HeroListState()
    data class Heroes(val heroes: List<Hero>) : HeroListState()
    data class Error(val error: String?) : HeroListState()

}
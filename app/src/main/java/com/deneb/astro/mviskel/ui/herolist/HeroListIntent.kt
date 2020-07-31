package com.deneb.astro.mviskel.ui.herolist

import com.deneb.astro.mviskel.data.model.Hero

sealed class HeroListIntent {

    object FetchHeros : HeroListIntent()
    //data class DetailHero(val hero: Hero): HeroListIntent()

}
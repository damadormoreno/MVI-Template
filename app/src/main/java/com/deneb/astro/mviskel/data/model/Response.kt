package com.deneb.astro.mviskel.data.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("results")
    val results: List<Hero>
)
package com.deneb.astro.mviskel.data.model


import com.google.gson.annotations.SerializedName

data class Assets(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
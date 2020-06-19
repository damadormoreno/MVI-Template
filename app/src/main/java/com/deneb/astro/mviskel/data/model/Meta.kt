package com.deneb.astro.mviskel.data.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("apiVersion")
    val apiVersion: String,
    @SerializedName("requestDate")
    val requestDate: String
)
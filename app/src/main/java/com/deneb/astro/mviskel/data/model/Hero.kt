package com.deneb.astro.mviskel.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hero(
    @SerializedName("assets")
    val assets: Assets,
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("buffs")
    val buffs: List<Any>,
    @SerializedName("common")
    val common: List<Int>,
    @SerializedName("debuffs")
    val debuffs: List<Any>,
    @SerializedName("devotion")
    val devotion: Devotion,
    @SerializedName("_id")
    val id: String,
    @SerializedName("moonlight")
    val moonlight: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rarity")
    val rarity: Int,
    @SerializedName("role")
    val role: String,
    @SerializedName("self_devotion")
    val selfDevotion: SelfDevotion,
    @SerializedName("zodiac")
    val zodiac: String
) : Serializable
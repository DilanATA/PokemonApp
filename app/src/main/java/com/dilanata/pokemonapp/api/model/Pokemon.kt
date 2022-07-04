package com.dilanata.pokemonapp.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Pokemon(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    var next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<Result>? = null
): Parcelable
package com.dilanata.pokemonapp.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Result(
    @SerialName("name")
    var name: String? = null,
    @SerialName("url")
    val url: String? = null
): Parcelable {
    constructor(name: String?) : this() {
        this.name = name
    }
}
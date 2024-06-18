package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class Luggage(
    @SerializedName("has_luggage") val hasLuggage: Boolean,
    @SerializedName("price") val price: Price? = null
)

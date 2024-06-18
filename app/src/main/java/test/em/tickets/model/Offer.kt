package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: Price
)

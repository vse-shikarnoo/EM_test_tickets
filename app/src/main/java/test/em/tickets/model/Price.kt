package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("value") val value: String
)

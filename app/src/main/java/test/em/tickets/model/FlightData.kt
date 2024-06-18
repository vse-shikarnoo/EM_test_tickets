package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class FlightData(
    @SerializedName("town") val town: String,
    @SerializedName("date") val date: String,
    @SerializedName("airport") val airport: String
)

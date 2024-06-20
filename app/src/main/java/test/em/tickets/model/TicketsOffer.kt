package test.em.tickets.model

import com.google.gson.annotations.SerializedName
import test.em.tickets.R

data class TicketsOffer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: Price,
){
    var color = R.color.white
}

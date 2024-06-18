package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class TicketsOfferResponse(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketsOffer>
)

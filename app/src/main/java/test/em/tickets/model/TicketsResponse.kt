package test.em.tickets.model

import com.google.gson.annotations.SerializedName

data class TicketsResponse(
    @SerializedName("tickets") val tickets: List<Ticket>
)
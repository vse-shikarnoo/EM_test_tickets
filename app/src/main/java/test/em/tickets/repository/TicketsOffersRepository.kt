package test.em.tickets.repository

import test.em.tickets.R
import test.em.tickets.model.TicketsOfferResponse
import test.em.tickets.remote.TicketsService

class TicketsOffersRepository {

    private val service = TicketsService.create()

    suspend fun getTicketsOffers(): TicketsOfferResponse {
        val res = service.getTicketsOffers()
        for (i in 0 until res.ticketsOffers.size) {
            res.ticketsOffers[i].color = when (i) {
                0 -> R.color.red
                1 -> R.color.blue
                2 -> R.color.white
                else -> R.color.white
            }
        }
        return res
    }
}
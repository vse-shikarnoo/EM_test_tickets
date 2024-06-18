package test.em.tickets.repository

import test.em.tickets.model.OffersResponse
import test.em.tickets.remote.TicketsService

class OffersRepository {

    val service = TicketsService.create()


    suspend fun getOffers():OffersResponse{
        return service.getOffers()
    }
}
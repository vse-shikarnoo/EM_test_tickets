package test.em.tickets.repository

import android.util.Log
import test.em.tickets.model.TicketsResponse
import test.em.tickets.remote.TicketsService

class TicketsRepository {
    private val service = TicketsService.create()

    suspend fun getTickets(): TicketsResponse {
        Log.d("Test", service.getTickets().toString())
        return service.getTickets()
    }
}
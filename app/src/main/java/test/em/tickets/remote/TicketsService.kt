package test.em.tickets.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import test.em.tickets.BuildConfig
import test.em.tickets.model.OffersResponse
import test.em.tickets.model.TicketsOfferResponse
import test.em.tickets.model.TicketsResponse

interface TicketsService {

    // https://run.mocky.io/v3/3fa9ae95-5aee-49a8-972f-50900f954cd9
    @GET("3fa9ae95-5aee-49a8-972f-50900f954cd9")
    suspend fun getOffers(): OffersResponse

    // https://run.mocky.io/v3/eeb10073-a1a4-4813-81bc-d310f38ff7a3
    @GET("eeb10073-a1a4-4813-81bc-d310f38ff7a3")
    suspend fun getTicketsOffers(): TicketsOfferResponse

    //https://run.mocky.io/v3/f55d30d7-86cf-40c3-8edf-69c3942726d6
    @GET("f55d30d7-86cf-40c3-8edf-69c3942726d6")
    suspend fun getTickets(): TicketsResponse

    companion object {
        fun create(): TicketsService {
            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(TicketsService::class.java)
        }
    }
}
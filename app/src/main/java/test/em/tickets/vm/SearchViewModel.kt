package test.em.tickets.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.em.tickets.model.Offer
import test.em.tickets.model.TicketsOffer
import test.em.tickets.repository.TicketsOffersRepository

class SearchViewModel: ViewModel() {

    private val repository = TicketsOffersRepository()

    private val ticketsOffersListLiveData = MutableLiveData<List<TicketsOffer>>()
    private val errorStateLiveData = MutableLiveData<Unit>()

    val ticketsOffersList: LiveData<List<TicketsOffer>>
        get() = ticketsOffersListLiveData

    val errorState: LiveData<Unit>
        get() = errorStateLiveData

    fun getTicketsOffers(){
        viewModelScope.launch {
            try {
                ticketsOffersListLiveData.postValue(repository.getTicketsOffers().ticketsOffers)
            }catch (t:Throwable){
                Log.e("ERROR", "getTicketsOffers: ${t.printStackTrace()}")
                errorStateLiveData.postValue(Unit)
            }
        }
    }
}
package test.em.tickets.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.em.tickets.model.Ticket
import test.em.tickets.repository.TicketsRepository

class AllTicketsViewModel: ViewModel() {

    private val repository = TicketsRepository()

    private val ticketsListLiveData = MutableLiveData<List<Ticket>>()
    private val errorStateLiveData = MutableLiveData<Unit>()

    val ticketsOffersList: LiveData<List<Ticket>>
        get() = ticketsListLiveData

    val errorState: LiveData<Unit>
        get() = errorStateLiveData

    fun getTicketsOffers(){
        viewModelScope.launch {
            try {
                ticketsListLiveData.postValue(repository.getTickets().tickets)
            }catch (t:Throwable){
                Log.e("ERROR", "getTicketsOffers: ${t.printStackTrace()}")
                errorStateLiveData.postValue(Unit)
            }
        }
    }
}
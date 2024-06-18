package test.em.tickets.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.em.tickets.model.Offer
import test.em.tickets.repository.OffersRepository

class TicketsViewModel:ViewModel() {

    private val repository = OffersRepository()

    private val offersListLiveData = MutableLiveData<List<Offer>>()
    private val errorStateLiveData = MutableLiveData<Unit>()

    val offersList: LiveData<List<Offer>>
        get() = offersListLiveData

    val errorState: LiveData<Unit>
        get() = errorStateLiveData

    fun getOffers(){
        viewModelScope.launch {
            try {
                offersListLiveData.postValue(repository.getOffers().offers)
            }catch (t:Throwable){
                Log.d("Test error", "getOffers: ${t.printStackTrace()}")
                errorStateLiveData.postValue(Unit)
            }
        }
    }
}
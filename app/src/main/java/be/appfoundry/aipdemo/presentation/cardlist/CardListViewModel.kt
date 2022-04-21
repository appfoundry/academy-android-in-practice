package be.appfoundry.aipdemo.presentation.cardlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data
import be.appfoundry.aipdemo.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _cards: MutableLiveData<Data<List<Card>>> = MutableLiveData()
    val cards: LiveData<Data<List<Card>>> = _cards

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            try {
                val cardsData = cardRepository.getCards()
                _cards.postValue(cardsData)
            } catch (e: Exception) {
                TODO("Add some better error handling!")
            }
        }
    }

    fun cardClicked(card: Card) {
        Log.d("CardListViewModel", "Card clicked: ${card.name}")
    }
}

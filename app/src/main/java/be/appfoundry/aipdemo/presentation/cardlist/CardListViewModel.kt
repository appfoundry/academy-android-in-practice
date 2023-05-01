package be.appfoundry.aipdemo.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.appfoundry.aipdemo.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardListState())
    val uiState: StateFlow<CardListState> = _uiState

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            cardRepository.getCards()
                .onStart { _uiState.update { it.loading() } }
                .catch { _uiState.update { it.error() } }
                .collect { data -> _uiState.update { it.data(data.data, data.source) } }
        }
    }
}

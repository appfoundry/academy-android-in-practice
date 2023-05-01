package be.appfoundry.aipdemo.presentation.cardlist

import androidx.compose.runtime.Immutable
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data.Source

@Immutable
data class CardListState(
    val isLoading: Boolean = false,
    val cards: List<Card>? = null,
    val source: Source? = null,
    val hasError: Boolean = false
) {
    fun loading() = this.copy(
        isLoading = true
    )

    fun data(cards: List<Card>, source: Source) = this.copy(
        isLoading = false,
        cards = cards,
        source = source,
        hasError = false
    )

    fun error() = this.copy(
        isLoading = false,
        hasError = true
    )
}

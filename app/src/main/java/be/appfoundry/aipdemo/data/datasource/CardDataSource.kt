package be.appfoundry.aipdemo.data.datasource

import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.network.CardService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CardDataSource {

    fun getCards(): Flow<List<Card>>
}

class RemoteCardDataSource @Inject constructor(
    private val cardService: CardService
) : CardDataSource {

    override fun getCards(): Flow<List<Card>> = flow {
        emit(cardService.getCards().cards)
    }
}

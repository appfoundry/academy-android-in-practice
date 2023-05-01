package be.appfoundry.aipdemo.data.repository

import be.appfoundry.aipdemo.data.database.CardDao
import be.appfoundry.aipdemo.data.datasource.CardDataSource
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardDataSource: CardDataSource,
    private val cardDao: CardDao
) {

    suspend fun getCards(): Flow<Data<List<Card>>> =
        cardDataSource.getCards()
            .onEach { cardDao.insertCards(it) }
            .map { Data(it, Data.Source.NETWORK) }
            .catch { emit(Data(cardDao.getCards(), Data.Source.DATABASE)) }
}

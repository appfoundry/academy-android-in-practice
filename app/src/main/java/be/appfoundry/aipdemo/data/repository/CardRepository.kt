package be.appfoundry.aipdemo.data.repository

import be.appfoundry.aipdemo.data.database.CardDao
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data
import be.appfoundry.aipdemo.data.network.CardService
import java.lang.Exception
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardService: CardService,
    private val cardDao: CardDao
) {

    suspend fun getCards(): Data<List<Card>> {
        return try {
            val cards = cardService.getCards().cards
            cardDao.insertCards(cards)
            Data(cards, Data.Source.NETWORK)
        } catch (e: Exception) {
            val cards = cardDao.getCards()
            Data(cards, Data.Source.DATABASE)
        }
    }
}
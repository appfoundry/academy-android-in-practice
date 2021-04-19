package be.appfoundry.aipdemo.data.repository

import be.appfoundry.aipdemo.data.database.CardDao
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.network.CardService
import java.lang.Exception
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardService: CardService,
    private val cardDao: CardDao
) {

    suspend fun getCards(): List<Card> {
        return try {
            val cards = cardService.getCards().cards
            cardDao.insertCards(cards)
            cards
        } catch (e: Exception) {
            cardDao.getCards()
        }
    }
}
package be.appfoundry.aipdemo.data.network

import be.appfoundry.aipdemo.data.model.CardList
import retrofit2.http.GET

interface CardService {

    @GET("/v1/cards")
    suspend fun getCards(): CardList
}

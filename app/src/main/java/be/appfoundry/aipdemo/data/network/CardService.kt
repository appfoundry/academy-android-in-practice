package be.appfoundry.aipdemo.data.network

import be.appfoundry.aipdemo.data.model.CardList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CardService {

    @GET("/v1/cards")
    suspend fun getCards(): CardList
}

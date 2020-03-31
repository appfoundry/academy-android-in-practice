package be.appfoundry.aipdemo.service

import be.appfoundry.aipdemo.model.StarWarsFilm
import be.appfoundry.aipdemo.model.StarWarsFilms
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SwapiService {

    @GET("/api/films")
    fun getFilms(): Observable<StarWarsFilms>

    @GET("/api/films/{id}")
    fun getFilm(@Path("id") id: Int): Call<StarWarsFilm>
}

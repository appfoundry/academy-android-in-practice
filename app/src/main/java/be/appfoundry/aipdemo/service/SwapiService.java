package be.appfoundry.aipdemo.service;

import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.model.StarWarsFilms;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiService {

    @GET("/api/films")
    Observable<StarWarsFilms> getFilms();

    @GET("/api/films/{id}")
    Call<StarWarsFilm> getFilm(@Path("id") int id);

}

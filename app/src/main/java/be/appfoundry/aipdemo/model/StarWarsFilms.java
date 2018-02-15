package be.appfoundry.aipdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsFilms {

    @JsonProperty("results")
    List<StarWarsFilm> results;

    public List<StarWarsFilm> getResults() {
        return results;
    }
}
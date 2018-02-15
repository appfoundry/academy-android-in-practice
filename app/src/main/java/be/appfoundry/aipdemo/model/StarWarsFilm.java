package be.appfoundry.aipdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import be.appfoundry.aipdemo.database.AppDatabase;

@Table(database = AppDatabase.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsFilm {

    @PrimaryKey
    @JsonProperty("episode_id")
    int episodeId;

    @Column
    @JsonProperty("title")
    String title;

    @Column
    @JsonProperty("opening_crawl")
    String openingCrawl;

    public int getEpisodeId() {
        return episodeId;
    }

    public String getTitle() {
        return title;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }
}
package be.appfoundry.aipdemo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class StarWarsFilm(

    @JsonProperty("episode_id") val episodeId: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("opening_crawl") val openingCrawl: String
)
package com.dorianlamande.repository.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChuckNorrisJokeResponse(
        @JsonProperty("type")
        val type:String,
        @JsonProperty("value")
        val jokes:List<JokeModel>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JokeModel(
        @JsonProperty("id")
        val id:Int,
        @JsonProperty("joke")
        val joke:String
)

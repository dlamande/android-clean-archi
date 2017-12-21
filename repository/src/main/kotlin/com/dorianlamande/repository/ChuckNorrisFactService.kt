package com.dorianlamande.repository

import com.dorianlamande.repository.models.ChuckNorrisJokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisFactService {
    @GET("jokes")
    fun getJokes(): Call<ChuckNorrisJokeResponse>
}
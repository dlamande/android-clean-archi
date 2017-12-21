package com.dorianlamande.repository.jokes

import com.dorianlamande.core.entities.Fact
import com.dorianlamande.core.repositories.FactsRepository
import com.dorianlamande.repository.ChuckNorrisFactService
import com.dorianlamande.repository.models.ChuckNorrisJokeResponse
import java.io.IOException

class FactsRepositoryImpl(private val service: ChuckNorrisFactService) : FactsRepository {

    @Throws(FactsRepository.Failure::class)
    override fun getFacts(): List<Fact> {
        try {
            val response = service.getJokes().execute()
            val jsonList = response.body() ?: throw FactsRepository.Failure()

            return transformToEntity(jsonList)
        } catch (e: IOException) {
            throw FactsRepository.Failure()
        }
    }

    private fun transformToEntity(jsonList: ChuckNorrisJokeResponse): List<Fact> {
        val jokes = ArrayList<Fact>()

        jsonList.jokes.map {
            jokes.add(Fact(it.joke))
        }

        return jokes
    }
}
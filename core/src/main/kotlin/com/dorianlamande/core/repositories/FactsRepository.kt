package com.dorianlamande.core.repositories

import com.dorianlamande.core.entities.Fact

interface FactsRepository {
    @Throws(Failure::class)
    fun getFacts() : List<Fact>

    class Failure : Exception()
}
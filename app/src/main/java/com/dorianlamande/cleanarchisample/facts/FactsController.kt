package com.dorianlamande.cleanarchisample.facts

import com.dorianlamande.core.interactors.FactsInteractor

interface FactsController {
    fun loadFacts()
}

class FactsControllerImpl(private val interactor: FactsInteractor) : FactsController {
    override fun loadFacts() {
        interactor.loadFacts()
    }
}
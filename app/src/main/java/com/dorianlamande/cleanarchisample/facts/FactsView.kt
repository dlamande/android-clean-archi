package com.dorianlamande.cleanarchisample.facts

interface FactsView {
    fun displayFacts(facts: List<FactViewModel>)
    fun displayError(errorMessage: String)
}
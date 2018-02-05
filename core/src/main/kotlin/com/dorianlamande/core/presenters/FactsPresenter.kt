package com.dorianlamande.core.presenters

import com.dorianlamande.core.entities.Fact

interface FactsPresenter {
    fun presentFacts(facts: List<Fact>)
    fun presentError()
}
package com.dorianlamande.cleanarchisample.facts

import android.content.res.Resources
import com.dorianlamande.cleanarchisample.R
import com.dorianlamande.core.entities.Fact
import com.dorianlamande.core.presenters.FactsPresenter

class FactsPresenterImpl(private val view: FactsView, private val resources: Resources) : FactsPresenter {
    override fun presentFacts(facts: List<Fact>) {
        view.displayFacts(facts.map { FactViewModel(it.label) })
    }

    override fun displayError() {
        view.displayError(resources.getString(R.string.errorMessage))
    }
}
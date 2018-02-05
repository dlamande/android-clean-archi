package com.dorianlamande.core.interactors

import com.dorianlamande.core.presenters.FactsPresenter
import com.dorianlamande.core.repositories.FactsRepository

open class FactsInteractor(private val repository: FactsRepository, private val presenter: FactsPresenter) {
    open fun loadFacts() {
        try {
            presenter.presentFacts(repository.getFacts().sortedBy { it.label })
        } catch (e: FactsRepository.Failure) {
            presenter.presentError()
        }
    }
}
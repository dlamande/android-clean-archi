package com.dorianlamande.core.interactors

import com.dorianlamande.core.entities.Fact
import com.dorianlamande.core.presenters.FactsPresenter
import com.dorianlamande.core.repositories.FactsRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsInteractorTest {
    @Mock private lateinit var presenter: FactsPresenter
    @Mock private lateinit var repository: FactsRepository
    @InjectMocks private lateinit var interactor: FactsInteractor

    @Test
    fun `Given a repository returning an error When get Facts Then Should display an Error`() {
        //GIVEN
        given(repository.getFacts()).willThrow(FactsRepository.Failure::class.java)

        //WHEN
        interactor.loadFacts()

        //THEN
        then(presenter).should(only()).presentError()
    }

    @Test
    fun `Given a repository returning facts When get Facts Then sould present Facts`() {
        //GIVEN
        given(repository.getFacts()).willReturn(
                listOf(Fact("A fact with B"), Fact("A fact with A")))

        //WHEN
        interactor.loadFacts()

        //THEN
        val expected = listOf(Fact("A fact with A"), Fact("A fact with B"))
        then(presenter).should(only()).presentFacts(expected)
    }
}
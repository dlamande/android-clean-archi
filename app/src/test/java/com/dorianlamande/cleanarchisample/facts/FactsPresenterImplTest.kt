package com.dorianlamande.cleanarchisample.facts

import android.content.res.Resources
import com.dorianlamande.cleanarchisample.R
import com.dorianlamande.core.entities.Fact
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsPresenterImplTest {
    @Mock private lateinit var view: FactsView
    @Mock private lateinit var resources: Resources
    @InjectMocks private lateinit var presenter: FactsPresenterImpl

    @Test
    fun givenFactsWhenPresentFactsThenDisplayFactsViewModel() {
        //GIVEN
        val facts = listOf(Fact("Just a fact"))

        //WHEN
        presenter.presentFacts(facts)

        //THEN
        then(view).should(only()).displayFacts(listOf(FactViewModel("Just a fact")))
    }

    @Test
    fun whenPresentErrorThenDisplayErrorWithCorrectMessage() {
        //GIVEN
        given(resources.getString(R.string.errorMessage)).willReturn("errorMessage")

        //WHEN
        presenter.presentError()

        //THEN
        then(view).should(only()).displayError("errorMessage")
    }
}
package com.dorianlamande.cleanarchisample.facts

import com.dorianlamande.core.interactors.FactsInteractor
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.only
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsControllerImplTest {
    @Mock private lateinit var interactor:FactsInteractor
    @InjectMocks private lateinit var controller:FactsControllerImpl

    @Test
    fun whenLoadFactThenShouldCallLoadFactsOfInteractor() {
        //WHEN
        controller.loadFacts()

        //THEN
        then(interactor).should(only()).loadFacts()
    }
}
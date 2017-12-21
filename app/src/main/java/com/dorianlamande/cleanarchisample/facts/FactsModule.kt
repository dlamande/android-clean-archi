package com.dorianlamande.cleanarchisample.facts

import android.content.res.Resources
import com.dorianlamande.cleanarchisample.FeatureScope
import com.dorianlamande.cleanarchisample.MainThreadExecutor
import com.dorianlamande.core.interactors.FactsInteractor
import com.dorianlamande.core.presenters.FactsPresenter
import com.dorianlamande.core.repositories.FactsRepository
import com.nicolasmouchel.executordecorator.ImmutableExecutorDecorator
import com.nicolasmouchel.executordecorator.MutableDecorator
import com.nicolasmouchel.executordecorator.MutableExecutorDecorator
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

@Module
class FactsModule {

    @ImmutableExecutorDecorator
    @Provides
    fun provideFactsController(interactor: FactsInteractor, executor: Executor): FactsController {
        val factsControllerImpl = FactsControllerImpl(interactor)
        return FactsControllerDecorator(executor, factsControllerImpl)
    }


    @Provides
    fun provideFactsInteractor(repository: FactsRepository, presenter: FactsPresenter): FactsInteractor =
            FactsInteractor(repository, presenter)

    @Provides
    fun provideFactsPresenter(view: FactsView, resources: Resources): FactsPresenter =
            FactsPresenterImpl(view, resources)

    @MutableExecutorDecorator
    @Provides
    fun provideFactsView(viewDecorator: MutableDecorator<FactsView>): FactsView =
            viewDecorator.asDecorated()

    @FeatureScope
    @Provides
    fun provideFactsViewDecorator(executor: MainThreadExecutor): MutableDecorator<FactsView> =
            FactsViewDecorator(executor)

}
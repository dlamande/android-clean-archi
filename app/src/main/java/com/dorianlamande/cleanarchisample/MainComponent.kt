package com.dorianlamande.cleanarchisample

import com.dorianlamande.cleanarchisample.facts.FactsComponent
import com.dorianlamande.cleanarchisample.facts.FactsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(MainModule::class), (NetworkModule::class)])
interface MainComponent {
    fun plus(module: FactsModule): FactsComponent
}
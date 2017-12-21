package com.dorianlamande.cleanarchisample.facts

import com.dorianlamande.cleanarchisample.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [(FactsModule::class)])
interface FactsComponent {
    fun inject(activity: FactsActivity)
}
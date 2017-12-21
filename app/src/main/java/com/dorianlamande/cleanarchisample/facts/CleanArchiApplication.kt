package com.dorianlamande.cleanarchisample.facts

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.dorianlamande.cleanarchisample.DaggerMainComponent
import com.dorianlamande.cleanarchisample.MainComponent
import com.dorianlamande.cleanarchisample.MainModule

class CleanArchiApplication : Application() {
    private lateinit var component: MainComponent

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        fun getComponent(context: Context): MainComponent {
            val application = context.applicationContext as CleanArchiApplication
            return application.component
        }

    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = DaggerMainComponent.builder().mainModule(MainModule(this)).build()
    }
}
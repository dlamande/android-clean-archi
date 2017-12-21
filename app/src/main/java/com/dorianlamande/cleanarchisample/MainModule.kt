package com.dorianlamande.cleanarchisample

import android.content.Context
import android.content.res.Resources
import com.dorianlamande.core.repositories.FactsRepository
import com.dorianlamande.repository.ChuckNorrisFactService
import com.dorianlamande.repository.jokes.FactsRepositoryImpl
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainModule(val context: Context) {
    companion object {
        const val APPLICATION = "application_context"
    }

    @Singleton
    @Provides
    fun provideBackgroundExecutor(): Executor = Executors.newFixedThreadPool(1)

    @Singleton
    @Provides
    fun provideHandlerExecutor(): MainThreadExecutor = MainThreadExecutor()

    @Named(APPLICATION)
    @Provides
    fun providesContext(): Context = context

    @Provides
    fun providesResources(): Resources = context.resources

    @Provides
    fun provideFactsRepository(retrofit: Retrofit): FactsRepository =
            FactsRepositoryImpl(retrofit.create(ChuckNorrisFactService::class.java))

    @Singleton
    @Provides
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper()
                .registerModule(KotlinModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
}
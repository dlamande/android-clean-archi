package com.dorianlamande.cleanarchisample

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object Scarlett {
        private val BASE_URL = "https://api.icndb.com/"
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
            OkHttpClient()
                .newBuilder()
                .build()

    @Provides
    fun provideRetrofit(mapper: ObjectMapper, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(Scarlett.BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
}
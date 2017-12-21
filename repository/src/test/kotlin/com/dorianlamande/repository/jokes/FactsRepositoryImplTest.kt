package com.dorianlamande.repository.jokes

import com.dorianlamande.core.entities.Fact
import com.dorianlamande.core.repositories.FactsRepository
import com.dorianlamande.repository.ChuckNorrisFactService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.*

class FactsRepositoryImplTest {
    private val server = MockWebServer()
    private lateinit var service: ChuckNorrisFactService
    private lateinit var repository: FactsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val mapper = ObjectMapper()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        service = Retrofit.Builder()
                .baseUrl(server.url("").toString())
                .addConverterFactory(JacksonConverterFactory.create(mapper.registerModule(KotlinModule())))
                .client(client)
                .build()
                .create(ChuckNorrisFactService::class.java)

        repository = FactsRepositoryImpl(service)
    }

    @Test(expected = FactsRepository.Failure::class)
    fun `When Service Have no body should Throws a Failure Exception`() {
        //GIVEN
        val inputStream = javaClass.classLoader.getResourceAsStream("")
        server.enqueue(MockResponse().setBody(Buffer().readFrom(inputStream)))

        //WHEN
        repository.getFacts()
    }

    @Test
    fun `When Service Have a valid body should return List of Jokes`() {
        //GIVE
        val inputStream = javaClass.classLoader.getResourceAsStream("Jokes.json")
        server.enqueue(MockResponse().setBody(Buffer().readFrom(inputStream)))

        //WHEN
        val jokes = repository.getFacts()

        //THEN
        val expectedJokes = Collections.singletonList(Fact("Chuck Norris uses ribbed condoms inside out, so he gets the pleasure."))

        Assert.assertEquals(expectedJokes, jokes)
    }
}
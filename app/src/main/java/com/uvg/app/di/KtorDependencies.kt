package com.uvg.app.di

import com.uvg.app.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object KtorDependencies {
    private var httpClient: HttpClient? = null
    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}
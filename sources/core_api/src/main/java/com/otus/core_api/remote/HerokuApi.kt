package com.otus.core_api.remote

import com.otus.core_api.dto.Elixir
import retrofit2.http.GET

interface HerokuApi {
    @GET("/Elixirs")
    suspend fun getElixirs(): List<Elixir>
}
package com.otus.core_api.remote

import retrofit2.http.GET
import com.otus.core_api.dto.Character

interface HpApi {
    @GET("/api/characters")
    suspend fun getAllCharacters(): List<Character>
}
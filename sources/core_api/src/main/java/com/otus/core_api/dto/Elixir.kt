package com.otus.core_api.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Elixir(
    val id: String,
    val name: String,
    val effect: String,
    val sideEffects: String,
    val time: String,
    val ingredients: List<Ingredient>): Parcelable

@Parcelize
data class Ingredient(val id: String,
                      val name: String): Parcelable
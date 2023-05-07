package com.otus.core_api.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(val id: String,
                     val name: String,
                     val species: String,
                     val gender: String,
                     val house: String,
                     val yearOfBirth: Int,
                     val wizard: Boolean,
                     val ancestry: String,
                     val eyeColour: String,
                     val hairColour: String,
                     val wand: WandAttr,
                     val patronus: String,
                     val hogwartsStudent: Boolean,
                     val hogwartsStaff: Boolean,
                     val actor: String,
                     val alive: Boolean,
                     val image: String): Parcelable

@Parcelize
data class WandAttr(val wood: String,
                    val core: String,
                    val length: Double): Parcelable
package com.example.arhitecturehw.data

import com.example.arhitecturehw.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsefulActivityDto(
    @Json(name = "activity") override val activity: String,
    @Json(name = "type")override val type: String,
    @Json(name = "key")override val key: Int
) : UsefulActivity
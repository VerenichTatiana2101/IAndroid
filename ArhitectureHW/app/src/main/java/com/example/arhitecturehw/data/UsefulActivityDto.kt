package com.example.arhitecturehw.data

import com.example.arhitecturehw.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsefulActivityDto(
    @Json(name = "activity") override val activity: String,
    @Json(name = "availability") override val availability: Int,
    @Json(name = "type")override val type: String,
    @Json(name = "participants") override val participants: Int,
    @Json(name = "price") override val price: Int,
    @Json(name = "accessibility") override val accessibility: String,
    @Json(name = "duration")override val duration: String,
    @Json(name = "kidFriendly")override val kidFriendly: String,
    @Json(name = "link")override val link: String,
    @Json(name = "key")override val key: Int
    ) : UsefulActivity
package com.codepath.flixstermovie2_p2

import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int,

    val name: String?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("known_for_department")
    val knownFor: String?,

    @SerializedName("birthday")
    val birthDate: String?,
)

data class ActorResponse(
    @SerializedName("results") val results: List<Actor>
)
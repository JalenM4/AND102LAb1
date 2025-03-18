package com.codepath.flixstermovie2_p2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("person/popular")
    fun getPopularActors(@Query("api_key") apiKey: String): Call<ActorResponse>
}
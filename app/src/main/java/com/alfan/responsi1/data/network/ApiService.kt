package com.alfan.responsi1.data.network

import com.alfan.responsi1.data.model.TeamResponse
import com.alfan.responsi1.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("teams/{id}")
    suspend fun getTeamDetails(
        @Path("id") teamId: Int,
        @Header("X-Auth-Token") token: String = Constants.API_TOKEN
    ): Response<TeamResponse>
}
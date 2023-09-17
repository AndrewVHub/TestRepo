package com.example.testrepo.data.network

import com.example.testrepo.data.network.model.ItemUserResponse
import com.example.testrepo.data.network.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/repositories")
    suspend fun getAllUsers(
        @Query("q") android: String,
    ): UsersResponse

    @GET("repositories/{repo_id}")
    suspend fun getUserById(
        @Path("repo_id") repoId: Int
    ): ItemUserResponse
}
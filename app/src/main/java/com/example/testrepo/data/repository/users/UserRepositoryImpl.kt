package com.example.testrepo.data.repository.users

import com.example.testrepo.data.network.Api
import com.example.testrepo.data.network.model.ItemUserResponse
import com.example.testrepo.data.network.model.UsersResponse
import com.example.testrepo.domain.repository.users.UserRepository

class UserRepositoryImpl(
    private val api: Api
) : UserRepository() {
    override suspend fun getAllUsers(): UsersResponse = api.getAllUsers("android")
    override suspend fun getUserByID(id: Int): ItemUserResponse = api.getUserById(id)
}
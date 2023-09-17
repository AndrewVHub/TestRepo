package com.example.testrepo.domain.repository.users

import com.example.testrepo.data.network.model.ItemUserResponse
import com.example.testrepo.data.network.model.UsersResponse

abstract class UserRepository {

    abstract suspend fun getAllUsers(): UsersResponse

    abstract suspend fun getUserByID(id: Int): ItemUserResponse
}
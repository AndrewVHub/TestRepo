package com.example.testrepo.ui.fragment.home.usecase

import com.example.testrepo.domain.mapper.toModel
import com.example.testrepo.domain.repository.users.UserRepository
import com.example.testrepo.ui.model.user.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetAllUsersUseCase(
    private val repository: UserRepository
) {
    fun getFlow(): Flow<List<UserModel>> = flow {
        emit(repository.getAllUsers().items.map { it.toModel() })
    }
}
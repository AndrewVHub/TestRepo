package com.example.testrepo.ui.fragment.home.detail

import com.example.testrepo.ui.fragment.base.BaseViewModel
import com.example.testrepo.ui.fragment.home.usecase.GetUserByIdUseCase
import com.example.testrepo.ui.model.user.UserModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UserDetailViewModel(
    private val useCase: GetUserByIdUseCase
) : BaseViewModel() {

    private val _userInfo = MutableSharedFlow<UserModel>()
    val userInfo = _userInfo.asSharedFlow()

    fun getUserById(id: Int) {

        useCase.getUserByID(id)
            .handleLoadingFlow()
            .onEach { emit(_userInfo, it) }
            .launchIn(this)
    }
}
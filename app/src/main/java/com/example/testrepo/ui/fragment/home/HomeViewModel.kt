package com.example.testrepo.ui.fragment.home

import androidx.lifecycle.viewModelScope
import com.example.testrepo.ui.fragment.base.BaseViewModel
import com.example.testrepo.ui.fragment.home.usecase.GetAllUsersUseCase
import com.example.testrepo.ui.model.user.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetAllUsersUseCase
) : BaseViewModel() {

    private val _userRepos = MutableStateFlow<List<UserModel>>(listOf())
    val userRepos = _userRepos.asStateFlow()

    init {
        loop()
    }

    fun navigateToDetailsUser(id: Int) {
        navigate(
            HomeFragmentDirections.actionHomeNavigationToUserDetailFragment(id)
        )
    }

    fun getUsersFlow() {
        useCase.getFlow()
            .handleLoadingFlow()
            .onEach { emit(_userRepos, it) }
            .launchIn(this)
    }

    private fun loop() {
        viewModelScope.launch {
            while (true) {
                getUsersFlow()
                delay(60000)
            }
        }
    }
}
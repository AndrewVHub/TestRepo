package com.example.testrepo.ui.fragment.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val errorHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }

    override val coroutineContext: CoroutineContext =
        Dispatchers.IO + SupervisorJob() + errorHandler

    protected val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Throwable>()
    val error = _error.asSharedFlow()

    protected val _navigate = MutableSharedFlow<NavDirections>()
    val navigate = _navigate.asSharedFlow()

    protected val _popBackStack = MutableSharedFlow<Unit>()
    val popBackStack = _popBackStack.asSharedFlow()

    protected fun handleError(throwable: Throwable) {
        Timber.tag(getViewModelTag()).wtf(throwable)
        emit(_error, throwable)
    }

    fun navigate(destination: NavDirections) {
        emit(_navigate, destination)
    }

    override fun onCleared() {
        Timber.tag(getViewModelTag()).i("CLEARED")
        coroutineContext.cancelChildren()
        super.onCleared()
    }

    fun popBackStack() {
        emit(_popBackStack, Unit)
    }

    protected fun <T> emit(flow: MutableStateFlow<T>, value: T) {
        viewModelScope.launch { flow.emit(value) }
    }

    protected fun <T> emit(flow: MutableSharedFlow<T>, value: T) {
        viewModelScope.launch { flow.emit(value) }
    }

    fun <T> Flow<T>.handleLoadingFlow() =
        onStart { emit(_loading, true) }
            .onCompletion { emit(_loading, false) }

    protected fun getViewModelTag() = this::class.simpleName ?: ""
}
package com.example.testrepo.di

import com.example.testrepo.BuildConfig
import com.example.testrepo.data.network.Api
import com.example.testrepo.data.repository.users.UserRepositoryImpl
import com.example.testrepo.domain.repository.users.UserRepository
import com.example.testrepo.ui.fragment.home.HomeViewModel
import com.example.testrepo.ui.fragment.home.detail.UserDetailViewModel
import com.example.testrepo.ui.fragment.home.usecase.GetAllUsersUseCase
import com.example.testrepo.ui.fragment.home.usecase.GetUserByIdUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewmodelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
}

val serviceModule = module {
    single<Api> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
}

val usersModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { GetAllUsersUseCase(get()) }
    single { GetUserByIdUseCase(get()) }
}
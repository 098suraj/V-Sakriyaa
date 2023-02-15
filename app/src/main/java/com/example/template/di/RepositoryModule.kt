package com.example.template.di


import com.example.template.data.repository.authRepo.AuthRepository
import com.example.template.data.repository.authRepo.AuthRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    @Singleton
    abstract fun bindAuthRepo(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

}
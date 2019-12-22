package com.example.justsomeapplication.di.user

import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.business.user.UserInteractor
import com.example.justsomeapplication.model.repository.user.IUserRepository
import com.example.justsomeapplication.model.repository.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface UserDependenciesModule {

    @Binds
    fun bindUserInteractor(userInteractor: UserInteractor): IUserInteractor

    @Binds
    fun bindUserRepo(userRepository: UserRepository): IUserRepository
}
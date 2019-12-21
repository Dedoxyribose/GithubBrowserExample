package com.example.justsomeapplication.di.main.fragment

import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.business.user.UserInteractor
import com.example.justsomeapplication.model.repository.user.IUserRepository
import com.example.justsomeapplication.model.repository.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface UserFragmentDependenciesModule {

    @Binds
    fun bindUserInteractor(userInteractor: UserInteractor): IUserInteractor

    @Binds
    fun bindUserRepo(userRepository: UserRepository): IUserRepository
}
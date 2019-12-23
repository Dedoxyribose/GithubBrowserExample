package com.example.justsomeapplication.di.user

import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.business.user.UserInteractor
import com.example.justsomeapplication.model.repository.repo.IRepoRepository
import com.example.justsomeapplication.model.repository.repo.RepoRepository
import com.example.justsomeapplication.model.repository.user.IUserRepository
import com.example.justsomeapplication.model.repository.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface UserDependenciesModule {

    @Binds
    @UserScope
    fun bindUserInteractor(userInteractor: UserInteractor): IUserInteractor

    @Binds
    @UserScope
    fun bindUserRepository(userRepository: UserRepository): IUserRepository

    @Binds
    @UserScope
    fun bindRepoRepository(repoRepository: RepoRepository): IRepoRepository
}
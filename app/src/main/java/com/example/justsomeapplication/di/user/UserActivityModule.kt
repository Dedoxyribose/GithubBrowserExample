package com.example.justsomeapplication.di.user

import com.example.justsomeapplication.di.user.repo.RepoListFragmentModule
import com.example.justsomeapplication.di.user.userinfo.UserFragmentModule
import com.example.justsomeapplication.ui.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserActivityModule {
    @UserScope
    @ContributesAndroidInjector(
        modules = [
            UserFragmentModule::class,
            UserDependenciesModule::class,
            RepoListFragmentModule::class
        ]
    )
    fun activity(): UserActivity?
}
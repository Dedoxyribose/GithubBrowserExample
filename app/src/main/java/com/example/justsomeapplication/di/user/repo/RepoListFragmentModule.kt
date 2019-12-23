package com.example.justsomeapplication.di.user.repo

import com.example.justsomeapplication.ui.user.repolist.RepoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RepoListFragmentModule {
    @RepoListFragmentScope
    @ContributesAndroidInjector
    fun fragment(): RepoListFragment?
}
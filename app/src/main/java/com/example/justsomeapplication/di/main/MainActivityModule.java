package com.example.justsomeapplication.di.main;

import com.example.justsomeapplication.di.main.fragment.MainFragmentModule;
import com.example.justsomeapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = {MainFragmentModule.class})
    MainActivity activity();
}

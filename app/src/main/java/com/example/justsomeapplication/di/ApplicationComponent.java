package com.example.justsomeapplication.di;

import android.content.Context;

import com.example.justsomeapplication.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        Builder appModule(AppModule appModule);

        ApplicationComponent build();
    }

    void inject(Application application);
}

package com.benmohammad.mvvm.di;

import com.benmohammad.mvvm.lobby.LobbyActivity;
import com.benmohammad.mvvm.lobby.LobbyModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract LobbyActivity bindLobbyActivity();
}

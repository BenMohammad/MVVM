package com.benmohammad.mvvm.lobby;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.benmohammad.mvvm.common.domain.interactors.LoadCommonGreetingUseCase;
import com.benmohammad.mvvm.rx.SchedulersFacade;

class LobbyViewModelFactory implements ViewModelProvider.Factory {

    private final LoadCommonGreetingUseCase loadCommonGreetingUseCase;
    private final LoadLobbyGreetingUseCase loadLobbyGreetingUseCase;
    private final SchedulersFacade schedulersFacade;

    
    public LobbyViewModelFactory(LoadCommonGreetingUseCase loadCommonGreetingUseCase, LoadLobbyGreetingUseCase loadLobbyGreetingUseCase, SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.loadLobbyGreetingUseCase = loadLobbyGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LobbyViewModel.class)) {
            return (T) new LobbyViewModel(loadCommonGreetingUseCase, loadLobbyGreetingUseCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown error...");
    }
}

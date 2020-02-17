package com.benmohammad.mvvm.lobby;

import com.benmohammad.mvvm.common.domain.interactors.LoadGreetingUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

class LoadLobbyGreetingUseCase implements LoadGreetingUseCase {

    private final LobbyGreetingRepository lobbyGreetingRepository;

    @Inject
    public LoadLobbyGreetingUseCase(LobbyGreetingRepository lobbyGreetingRepository) {
        this.lobbyGreetingRepository = lobbyGreetingRepository;
    }

    @Override
    public Single<String> execute() {
        return lobbyGreetingRepository.getGreeting();
    }
}

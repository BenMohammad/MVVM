package com.benmohammad.mvvm.lobby;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.benmohammad.mvvm.common.domain.interactors.LoadCommonGreetingUseCase;
import com.benmohammad.mvvm.common.domain.interactors.LoadGreetingUseCase;
import com.benmohammad.mvvm.common.viewmodel.Response;
import com.benmohammad.mvvm.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

class LobbyViewModel extends ViewModel {

    private final LoadCommonGreetingUseCase loadCommonGreetingUseCase;
    private final LoadLobbyGreetingUseCase loadLobbyGreetingUseCase;
    private final SchedulersFacade schedulersFacade;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Response> response = new MutableLiveData<>();

    public LobbyViewModel(LoadCommonGreetingUseCase loadCommonGreetingUseCase, LoadLobbyGreetingUseCase loadLobbyGreetingUseCase, SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.loadLobbyGreetingUseCase = loadLobbyGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
    }

    void loadCommonGreeting() {
        loadGreeting(loadCommonGreetingUseCase);
    }

    void loadLobbyGreeting() {
        loadGreeting(loadLobbyGreetingUseCase);
    }

    MutableLiveData<Response> response() {
        return response;
    }

    private void loadGreeting(LoadGreetingUseCase loadGreetingUseCase) {
        compositeDisposable.add(loadGreetingUseCase.execute()
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .doOnSubscribe(__ -> response.setValue(Response.loading()))
        .subscribe(
                greeting -> response.setValue(Response.success(greeting)),
                error -> response.setValue(Response.error(error))
        ));
    }
}

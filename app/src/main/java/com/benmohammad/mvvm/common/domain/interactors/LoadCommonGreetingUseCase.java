package com.benmohammad.mvvm.common.domain.interactors;

import com.benmohammad.mvvm.common.domain.model.CommonGreetingRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadCommonGreetingUseCase implements LoadGreetingUseCase {

    private final CommonGreetingRepository commonGreetingRepository;


    @Inject
    public LoadCommonGreetingUseCase(CommonGreetingRepository commonGreetingRepository) {
        this.commonGreetingRepository = commonGreetingRepository;
    }




    @Override
    public Single<String> execute() {
        return commonGreetingRepository.getGreeting();
    }
}

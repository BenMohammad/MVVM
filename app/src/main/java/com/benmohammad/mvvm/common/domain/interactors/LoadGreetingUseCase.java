package com.benmohammad.mvvm.common.domain.interactors;

import io.reactivex.Single;

public interface LoadGreetingUseCase {

    Single<String> execute();
}

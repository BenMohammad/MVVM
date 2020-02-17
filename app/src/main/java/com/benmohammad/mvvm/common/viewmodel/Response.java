package com.benmohammad.mvvm.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.benmohammad.mvvm.common.viewmodel.Status.*;

public class Response {

    public final Status status;

    @Nullable
    public final String data;

    @Nullable
    public final Throwable error;

    public Response(Status status, @Nullable String data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static Response success(@NonNull String data) {
        return new Response(SUCCESS, data, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}

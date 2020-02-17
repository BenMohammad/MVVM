package com.benmohammad.mvvm.lobby;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.benmohammad.mvvm.R;
import com.benmohammad.mvvm.common.viewmodel.Response;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LobbyActivity extends AppCompatActivity {


    @Inject
    LobbyViewModelFactory lobbyViewModelFactory;

    @BindView(R.id.greeting_textview)
    TextView greetingTextView;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    private LobbyViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, lobbyViewModelFactory).get(LobbyViewModel.class);
        viewModel.response().observe(this, response -> processResonse(response));
    }

    @OnClick(R.id.common_greeting_button)
    void onCommonGreetingClicked() {
        viewModel.loadCommonGreeting();
    }

    @OnClick(R.id.lobby_greeting_button)
    void onLobbyGreetingClicked() {
        viewModel.loadLobbyGreeting();
    }

    private void processResonse(Response response) {
        switch(response.status) {
            case LOADING:
                renderLoadState();
                break;
            case SUCCESS:
                renderDataState(response.data);
                break;
            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadState(){
        greetingTextView.setVisibility(View.GONE);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void renderDataState(String greeting) {
        loadingIndicator.setVisibility(View.GONE);
        greetingTextView.setVisibility(View.VISIBLE);
        greetingTextView.setText(greeting);
    }

    private void renderErrorState(Throwable error)  {
        Timber.e(error);
        loadingIndicator.setVisibility(View.GONE);
        greetingTextView.setVisibility(View.GONE);
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}

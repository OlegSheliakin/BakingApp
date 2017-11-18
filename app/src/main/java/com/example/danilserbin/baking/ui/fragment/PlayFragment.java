package com.example.danilserbin.baking.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.presenter.PlayPresenter;
import com.example.danilserbin.baking.ui.activity.FullscreenActivity;
import com.example.danilserbin.baking.ui.di.modul.PlayModule;
import com.example.danilserbin.baking.ui.view.PlayView;
import com.example.danilserbin.baking.util.ExoPlayerVideoSingleton;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayFragment extends Fragment implements PlayView {

    @BindView(R.id.video)
    SimpleExoPlayerView videoView;
    @BindView(R.id.tvVideo)
    TextView tvVideo;
    @BindView(R.id.btnBefore)
    Button beforeButton;
    @BindView(R.id.btnAfter)
    Button afterButton;
    @Inject
    PlayPresenter presenter;

    public PlayFragment(){
        BakingApp.getComponent().createPlayComponent(new PlayModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_play, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initial();

        presenter.play();
    }

    private void initial() {

        beforeButton.setOnClickListener(v -> presenter.clickBtnBefore());
        afterButton.setOnClickListener(v -> presenter.clickBtnAfter());

    }

    @Override
    public void beforeEnable(int visible) {
        beforeButton.setVisibility(visible);
    }

    @Override
    public void afterEnable(int visible) {
        afterButton.setVisibility(visible);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTextIngredient(String s) {
        tvVideo.setText(s);
    }

    @Override
    public void setVideo(String path) {
        videoView.setPlayer(null);
        if (!path.isEmpty()) {
            ExoPlayerVideoSingleton.getInstance().prepareExoPlayerForUri(
                    getContext(), Uri.parse(path), videoView);
            initFullscreenButton(path);
        } else {
            ExoPlayerVideoSingleton.getInstance().clearPlayer();
        }
    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    public void restart() {
        presenter.play();
    }

    private void initFullscreenButton(String path) {
        PlaybackControlView controlView = videoView.findViewById(R.id.exo_controller);
        FrameLayout mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FullscreenActivity.class);
            intent.putExtra("Full", path);
            startActivity(intent);
        });
    }
}
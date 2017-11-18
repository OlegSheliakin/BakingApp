package com.example.danilserbin.baking.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.util.ExoPlayerVideoSingleton;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullscreenActivity extends AppCompatActivity {

    @BindView(R.id.sepView)
    SimpleExoPlayerView exoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);

        initial();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String path = getIntent().getStringExtra("Full");

        ExoPlayerVideoSingleton.getInstance()
                .prepareExoPlayerForUri(this, Uri.parse(path),exoPlayerView);
    }

    private void initial() {
        PlaybackControlView controlView = exoPlayerView.findViewById(R.id.exo_controller);
        ImageView mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.ic_fullscreen_exit_black_24dp));
        FrameLayout mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(v -> finish());
    }

}
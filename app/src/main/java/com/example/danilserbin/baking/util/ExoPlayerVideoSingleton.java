package com.example.danilserbin.baking.util;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.SurfaceView;

import com.example.danilserbin.baking.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

public final class ExoPlayerVideoSingleton {

    private static ExoPlayerVideoSingleton instance;

    private SimpleExoPlayer player;
    private Uri playerUri;

    public static ExoPlayerVideoSingleton getInstance() {
        if (instance == null) {
            instance = new ExoPlayerVideoSingleton();
        }
        return instance;
    }

    public void prepareExoPlayerForUri(Context context, Uri uri, SimpleExoPlayerView exoPlayerView) {

        if (context != null && uri != null && exoPlayerView != null) {
            if (!uri.equals(playerUri) || player == null) {

                playerUri = uri;

                Handler mainHandler = new Handler();
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelection.Factory videoTrackSelectionFactory =
                        new AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector =
                        new DefaultTrackSelector(videoTrackSelectionFactory);

                DefaultLoadControl control = new DefaultLoadControl();

                RenderersFactory renderersFactory = new DefaultRenderersFactory(context);
                player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, control);

                exoPlayerView.setPlayer(player);

                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                        Util.getUserAgent(context, context.getResources().getString(R.string.app_name)),
                        (TransferListener<? super DataSource>) bandwidthMeter);
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource videoSource = new ExtractorMediaSource(uri,
                        dataSourceFactory, extractorsFactory, mainHandler, null);

                player.prepare(videoSource, false, false);
            }

            player.clearVideoSurface();
            player.setVideoSurfaceView(
                    (SurfaceView) exoPlayerView.getVideoSurfaceView());
            exoPlayerView.setPlayer(player);
        }
    }

    public void clearPlayer() {
        if (player != null) {
            player.stop();
            player.release();
        }

        player = null;
    }
}

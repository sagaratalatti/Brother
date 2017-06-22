package com.android.brother.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.brother.R;
import com.android.brother.entities.EventCard;
import com.android.brother.infrastructure.BrotherApplication;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 21-06-2017.
 */

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String videoUrl;
    public static String EXTRA_VIDEO_IFNO = "EXTRA_VIDEO_INFO";
    @BindView(R.id.youtube_player)
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);
        ButterKnife.bind(this);
        videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_IFNO);
        youTubePlayerView.initialize(BrotherApplication.YOUTUBE_API, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(videoUrl);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public static Intent newIntent(Context context, EventCard eventCard){
        Intent intent = new Intent(context, YoutubeActivity.class);
        intent.putExtra(EXTRA_VIDEO_IFNO, eventCard.getYoutubeEnding());
        return intent;
    }
}

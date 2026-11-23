package com.example.week12;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.IOException;

public class MediaPlayerManager {
    public static final int RAW = 1;
    public static final int ASSET = 2;
    public static final int FILE = 3;
    public static final int NETWORK = 4;
    private static final String SITE =
            "http://192.168.219.104:8080/Multimedia/davichi.mp3";

    private MediaPlayer player;

    public boolean isPlaying() {
        return player != null && player.isPlaying();
    }

    public int getDuration() {
        int result = 0;
        if (player != null) {
            result = player.getDuration();
        }
        return result;
    }

    public int getCurrentPosition() {
        int result = 0;

        if (player != null) {
            result = player.getCurrentPosition();
        }
        return result;
    }

    public int getAudioSessionId() {
        int result = 0;

        if (player != null) {
            result = player.getAudioSessionId();
        }
        return result;
    }

    public void play() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public void seekTo(int position) {
        if (player != null) {
            player.seekTo(position);
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
        }
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private MediaPlayer createPlayer() {
        MediaPlayer player = new MediaPlayer();
        player.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        );
        player.setLooping(false);
        return player;
    }

    public void load(Context context, int type,
                     MediaPlayer.OnPreparedListener listener) throws IOException {
        release();
        player = createPlayer();
        AssetFileDescriptor afd;
        switch (type) {
            case RAW:
                afd = context.getResources().openRawResourceFd(R.raw.konan);
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
                        afd.getLength());
                break;
            case ASSET:
                afd = context.getAssets().openFd("lemontree.mp3");
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
                        afd.getLength());
                break;
            case FILE:
                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/Music/";
                player.setDataSource(path + "풍문으로 들었소.mp3");
                break;
            case NETWORK:
                player.setDataSource(SITE);
                player.setOnPreparedListener(listener);
                player.prepareAsync();
        }
        if (type != NETWORK) {
            player.prepare();
            if (listener != null) {
                listener.onPrepared(player);
            }
        }
    }
}

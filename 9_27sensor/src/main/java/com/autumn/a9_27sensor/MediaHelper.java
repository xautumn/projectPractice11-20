package com.autumn.a9_27sensor;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.util.Log;



import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by wuqi on 2018/9/5.
 */
public class MediaHelper {

    private String TAG = "wq";
    private Context mContext;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    /**
     * 是否初始化标识
     */
    private boolean mIsInitialized;

    public MediaHelper(Context context) {
        this.mContext = context;
        initMedia();
    }

    private void initMedia() {
        audioManager = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
        audioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setWakeMode(mContext, PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        initMediaEvent();
    }

    private void initMediaEvent() {
        Log.i(TAG, "initMediaEvent");
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "mediaPlayer onPrepared");
                if (mp != mediaPlayer) {
                    Log.e(TAG, "mediaPlayer onPrepared error");
                    return; 
                }
                mIsInitialized = true;
                if (isAudioFocusSuccessful()) {
                    mediaPlayer.seekTo(0);
                }
            }
        });

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, "mediaPlayer onSeekComplete");
                isAudioFocusSuccessful();
                startPlay();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i(TAG, "mediaPlayer play is over");
                //释放焦点
                audioManager.abandonAudioFocus(mAudioFocusChangeListener);
                postMes();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "mediaPlayer onError what = " + what + ",extra = " + extra);
                mIsInitialized = false;
                audioManager.abandonAudioFocus(mAudioFocusChangeListener);
                postMes();
                return true;
            }
        });
    }

    /**
     * 开始播放排完声音
     */
    public void startDrainVoice() {
        Log.i(TAG,"startDrainVoice");
        Observable
                .create(new Observable.OnSubscribe<Void>() {

                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        try {
                            mIsInitialized = false;
                            if (mediaPlayer == null) {
                                initMedia();
                            }
                            Log.d(TAG, "setMediaPlayerDataSource mediaPlayer = " + mediaPlayer);
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                mediaPlayer.stop();
                            }
                            mediaPlayer.reset();
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            AssetFileDescriptor fd = mContext.getAssets().openFd("350_combine.mp3");
                            String videoUrl = "http://sc1.111ttt.cn:8282/2018/1/03m/13/396131229550.m4a?tflag=1519095601&pin=6cd414115fdb9a950d827487b16b5f97#.mp3";
                            mediaPlayer.setDataSource(mContext,Uri.parse(videoUrl));
                            mediaPlayer.prepare();
                        } catch (Exception e) {
                            subscriber.onError(e);
                            Log.e(TAG, "e = " + e);
                        }
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "setMediaPlayerDataSource failure = " + Log.getStackTraceString(e));
                        mIsInitialized = false;
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        Log.i(TAG, "onNext");
                    }
                });
    }

    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mIsInitialized = false;
    }

    public void pauseMedia() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        audioManager.abandonAudioFocus(mAudioFocusChangeListener);
    }

    private void startPlay() {
        if (isInitialed()) {
            Log.d(TAG, "startPlay is ready");
            mediaPlayer.start();
        } else {
            Log.d(TAG, "mediaPlayer is not Initialized");
        }
    }

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    Log.d(TAG, "onAudioFocusChange focusChange = " + focusChange);
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                postMes();
                            }
                            audioManager.abandonAudioFocus(mAudioFocusChangeListener);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                postMes();
                            }
                            audioManager.abandonAudioFocus(mAudioFocusChangeListener);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                postMes();
                            }
                            audioManager.abandonAudioFocus(mAudioFocusChangeListener);
                            break;
                        default:
                            break;
                    }
                }
            };

    private boolean isAudioFocusSuccessful() {
        int result = audioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    private boolean isInitialed() {
        return mIsInitialized;
    }

    private void postMes() {
        Log.i(TAG,"postMes");
        //EventBus.getDefault().post(new String("drain over"));
    }
}

package com.autumn.a9_27sensor;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class CloudMusicPlayService extends Service {
    private final static String TAG = CloudMusicPlayService.class.getSimpleName();

    private static final int FOREGROUND_ID = 1;

    private MediaPlayer mediaPlayer;
    private static final int MediaPlayState_Idle = 0;
    private static final int MediaPlayerState_Initialized = 1;
    private static final int MediaPlayState_Preparing = 2;
    private static final int MediaPlayState_Prepared = 3;
    private int mediaPlayerState = MediaPlayState_Idle;

    private AudioManager audioManager;

    // mediaPlayer 是否初始化好了
    private boolean mIsInitialized = false;


    // 耳机线控监听器
    private ComponentName mRemoteControlClientReceiverComponent;

    // 音频焦点失去是否是因为短暂停
    private boolean isAudioFocusLossTransient = false;

    private int mCurrentPlayIndex;


    private List<?> mMusicPlayList;
    private final int PROGRESS_MAX = 1000;

    public static int currentProgress = -1;
    
    //要移动的位置
    private int mSeekPosition;

    //播放下载歌曲失败尝试重播的次数
    private static int playNotDownloadRetryNum = 0;

    //获取私人FM失败尝试重播的次数
    private static int getPrivateFmRetryNum = 0;

    //获取私人FM失败尝试重播的次数
    private final static int GET_PRIVATE_FM_MAX_RETRYNUM = 5;


    private final static String MAP_KEY_CURRENT_MUSIC_NAME = "MAP_KEY_CURRENT_MUSIC_NAME";
    private final static String MAP_KEY_CURRENT_SINGER_NAME = "MAP_KEY_CURRENT_SINGER_NAME";

    //屏幕是否亮屏
    private static boolean mStatusScreenIsOn = true;

    // 刷新音乐进度条界面
    public static final int REFRESH_MUSIC_PROGRESS_UI = 0x01;
    // 刷新音乐进度条界面的间隔时间
    public static final int REFRESH_MUSIC_DELAY_TIME = 800;

    //Launcher上滑通知栏，是否上滑显示网易云音乐通知栏
    private static final String ACTION_PULL_UP_STATUS = "com.xtc.i3launcher.action.ACTION_PULL_UP_STATUS";
    //Launcher上滑通知栏，是否上滑
    private static final String EXTRA_PULL_UP_STATUS = "com.xtc.i3launcher.extra.EXTRA_PULL_UP_STATUS";
    //是否上滑
    private static boolean mNotificationIsPullUp = false;

    private static final String LAUNCHER_PACKAGE_NAME = "com.xtc.i3launcher";
    private static final String LAUNCHER_HOME_CLASS_NAME = "com.xtc.i3launcher.module.home.view.activity.HomeActivity";

    @Nullable
    private PowerManager.WakeLock stopWakeLock;

    private static final long STOP_WAKE_LOCK_TIMEOUT = 3 * 60 * 1000;

    // 记录第一次发现的不能播放的歌曲的index，避免列表所有不能播而自动下一首导致死循环
    private int firstCannotPlayMusicIndex = -1;

    private static final String NETEASE_OPEN_CHANGED_ACTION = "com.xtc.neteasecloud.open";
    private static final String NETEASE_OPEN_EXTRA = "isOpen";

    @Nullable
    private Boolean isOpen = null;

    private BroadcastReceiver mOpenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(LogTag.Default, "before receive isOpen=" + isOpen);
            if (Objects.equals(intent.getAction(), NETEASE_OPEN_CHANGED_ACTION)) {
                isOpen = intent.getBooleanExtra(NETEASE_OPEN_EXTRA, false);
            }
            Log.i(LogTag.Default, "after receive isOpen=" + isOpen);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, ">>>>>>> service onCreate <<<<<<<");
        super.onCreate();
        initMediaPlayer();
        initRegister();
    }


    @Override
    public void onDestroy() {
        //保存 播放的相关参数
        stopForeground(true);
        exit();
        unRegister();
        mIsInitialized = false;
        Log.d(TAG, ">>>>>>> service onDestroy <<<<<<<");
        super.onDestroy();
    }


    /**
     * 启动网易云音乐播放的Service
     *
     * @param mContext            应用上下文
     * @param fmServicePlayAction 网易云音乐播放的Service的Action类型
     */
    public static void startCloudMusicPlayService(Context mContext, String fmServicePlayAction) {
        Intent intent = new Intent(mContext, CloudMusicPlayService.class);
        intent.setAction(fmServicePlayAction);
        // 之前是隐式启动，有可能引发 ComponentName 为空的情况。改为显式
        // intent.setPackage(mContext.getPackageName());
        mContext.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LogTag.Play, "onStartCommand: intent=" + intent);
        String action = intent.getAction();
        Log.d(TAG, "command service action=" + action);
        if (TextUtils.isEmpty(action)) {
            return Service.START_NOT_STICKY;
        }
                prevOrNextMusic(true);
        return Service.START_NOT_STICKY;
    }



    @NonNull
    private String getSearchKeyword(@NonNull Intent intent) {
        String songName = intent.getStringExtra("songName");
        String artistName = intent.getStringExtra("artistName");
        Log.d(LogTag.Default, "getSearchKeyword() called with: songName=" + songName + " artistName" + artistName);
        String result = "";
        if (!TextUtils.isEmpty(artistName)) {
            result += artistName;
        }
        if (!TextUtils.isEmpty(songName)) {
            if (!Objects.equals(result, "")) {
                // 有艺术家姓名，拼接一个空格
                result = " " + songName;
            } else {
                result = songName;
            }
        }
        return result;
    }





    /**
     * mediaPlayer 是否准备好了
     */
    public boolean isInitialized() {
        return mIsInitialized;
    }



    /**
     * 设置MediaPlayer的播放参数
     *
     * @param songPlayUrl  播放地址
     * @param seekPosition 拖动到的位置
     */
    private void setMediaPlayerDataSource(final String songPlayUrl, final int seekPosition) {

        Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        try {
                            mIsInitialized = false; //还未初始化好
                            if (mediaPlayer == null) {
                                initMediaPlayer();
                            }
                            Log.d(TAG, "setMediaPlayerDataSource() mediaPlayer = " + mediaPlayer + ", threadName=" + Thread.currentThread().getName());
                            if (mediaPlayer.isPlaying()) {

                                acquireStopWakeLock();

                                mediaPlayer.pause();
                                mediaPlayer.stop();
                            }
                            mediaPlayerState = MediaPlayState_Idle;
                            mediaPlayer.reset();// 把各项参数恢复到初始状态
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
                            Log.d(TAG, "setMediaPlayerDataSource() 要播放歌曲的URL：" + songPlayUrl + " 要拖动到的进度条位置为：" + seekPosition);
                            mediaPlayerState = MediaPlayerState_Initialized;
                            mediaPlayer.setDataSource(songPlayUrl);
                            // mediaPlayer.prepare();// 进行缓冲
                            mediaPlayerState = MediaPlayState_Preparing;
                            mediaPlayer.prepareAsync();
                            mSeekPosition = seekPosition;
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                })
                // .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIsInitialized = false;
                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }


    /**
     * 退出MediaPlayer
     */
    private void exit() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 反注册一些广播和服务以及EventBus
     */
    private void unRegister() {
//        unregisterReceiver(mExternalStatusChangeReceiver);
        audioManager.unregisterMediaButtonEventReceiver(mRemoteControlClientReceiverComponent);
        audioManager.abandonAudioFocus(mAudioFocusChangeListener);
    }

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                }
            };

    /**
     * 初始化MediaPlayer
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        Log.d(TAG, ">>>>>>>>>>>>>>>initMediaPlayer() mediaPlayer = " + mediaPlayer);
        mediaPlayer.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, ">>>>>>>>>>>>>>> mediaPlayer onPrepared mediaPlayerState=" + mediaPlayerState);
                if (mp != mediaPlayer) {
                    return;  //如果不是一样的则return
                }
                if (mediaPlayerState == MediaPlayState_Preparing) {
                    mediaPlayerState = MediaPlayState_Prepared;
                    mIsInitialized = true;
                    if (isAudioFocusSuccessful()) {
                        int duration = mediaPlayer.getDuration();
                        int position = mSeekPosition * duration / PROGRESS_MAX;
                        Log.d(TAG, "播放总进度为：" + duration + " 要拖动到的播放位置为：" + position);
                        mediaPlayer.seekTo(position);
                    }
                }
            }
        });

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, ">>>>>>>>>>>>>>> mediaPlayer onSeekComplete mediaPlayerState=" + mediaPlayerState);
                if (mediaPlayerState == MediaPlayState_Prepared) {
                    startPlay();
                }
                releaseStopWakeLock();
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.d(TAG, ">>>>>>>>>>>>>>> mediaPlayer onBufferingUpdate , percent = " + percent);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(LogTag.Play, "mediaPlayer onCompletion() called with: mp = [" + mp + "]");
                        prevOrNextMusic(true);
            }
        });

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            stopWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "netease-stop");
        } else {
        }
    }

    /**
     * 初始化时候，注册一些广播和服务以及EventBus
     */
    private void initRegister() {
        IntentFilter mNetworkFilter = new IntentFilter();
        mNetworkFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        //注册屏幕亮屏、息屏时的广播
        IntentFilter mScreenFilter = new IntentFilter();
        mScreenFilter.addAction(Intent.ACTION_SCREEN_ON); // 亮屏
        mScreenFilter.addAction(Intent.ACTION_SCREEN_OFF);// 息屏

        //注册网易云音乐功能开关改变广播
        IntentFilter mOpenReceiverFilter = new IntentFilter();
        mOpenReceiverFilter.addAction(NETEASE_OPEN_CHANGED_ACTION);
        registerReceiver(mOpenReceiver, mOpenReceiverFilter);


        //获取音频服务
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        // 监听Audio焦点变化
        audioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
        audioManager.registerMediaButtonEventReceiver(mRemoteControlClientReceiverComponent);

    }


    /**
     * 判断焦点状态是否可用
     *
     * @return 音频是否可用
     */
    private boolean isAudioFocusSuccessful() {
        // Request audio focus for playback
        int result = audioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    private void startPlay() {
        if (isInitialized()) {
            Log.d(TAG, ">>>>>>>>>>>>>>> startPlay() 准备好了，开始播放");

            mediaPlayer.start();
            audioManager.registerMediaButtonEventReceiver(mRemoteControlClientReceiverComponent);
        } else {
            Log.d(TAG, ">>>>>>>>>>>>>>> startPlay() 准备失败，重新获取保存好的播放歌曲信息，然后重新播放");
        }
    }





    /**
     * 播放下一首播放
     */
    private void nextMusic() {
                prevOrNextMusic(false);
    }


    private void prevOrNextMusic(boolean isPrev) {
        String videoUrl = "http://sc1.111ttt.cn:8282/2018/1/03m/13/396131229550.m4a?tflag=1519095601&pin=6cd414115fdb9a950d827487b16b5f97#.mp3";
        setMediaPlayerDataSource(videoUrl,0);
    }


    // 息屏播放需要切换歌曲前需要获取锁，播放中的锁由mediaPlayer持有
    private void acquireStopWakeLock() {
        Log.d(LogTag.Default, "acquireStopWakeLock() called stopWakeLock=" + stopWakeLock);
        if (stopWakeLock != null) {
            boolean held = stopWakeLock.isHeld();
            Log.i(LogTag.Default, "stopWakeLock held=" + held);
            if (held) {
                stopWakeLock.release();
            }
            stopWakeLock.acquire(STOP_WAKE_LOCK_TIMEOUT);
        }
    }

    private void releaseStopWakeLock() {
        Log.d(LogTag.Default, "releaseStopWakeLock() called stopWakeLock=" + stopWakeLock);
        if (stopWakeLock != null) {
            boolean held = stopWakeLock.isHeld();
            Log.i(LogTag.Default, "stopWakeLock held=" + held);
            if (held) {
                stopWakeLock.release();
            }
        }
    }


}


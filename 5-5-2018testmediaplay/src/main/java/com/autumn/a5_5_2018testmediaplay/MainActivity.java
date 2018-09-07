package com.autumn.a5_5_2018testmediaplay;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private String TAG = "wq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initMediaPlayer();

    }

    private void initView() {
        View viewById = findViewById(R.id.tv);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick");
                String path = "storage/emulated/0/test.mp3";
                setMediaPlayerDataSource(path,0);
            }
        });
    }

    private void setMediaPlayerDataSource(String songPlayUrl, int seekPosition) {

        mediaPlayer.reset();// 把各项参数恢复到初始状态
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        Log.d(TAG, "setMediaPlayerDataSource() 要播放歌曲的URL：" + songPlayUrl + " 要拖动到的进度条位置为：" + seekPosition);
        try {
            mediaPlayer.setDataSource(songPlayUrl);
            mediaPlayer.prepare();// 进行缓冲
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initMediaPlayer() {
        //获取音频服务
        audioManager = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);


        mediaPlayer = new MediaPlayer();
        Log.d(TAG, "initMediaPlayer mediaPlayer = " + mediaPlayer + "mContext = " + mContext);
        mediaPlayer.setWakeMode(mContext, PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "mediaPlayer onPrepared");
                if (mp != mediaPlayer) {
                    Log.w(TAG, "onPrepared mp != mediaPlayer");
                    return;  //如果不是一样的则return
                }
                // 准备好后开始播放 听歌识曲循环播放
               /*     mCurrentMusicPlayType = DataHolder.getInstance().getMusicPlayType();
                    if (mCurrentMusicPlayType == IPlaySourceType.TYPE_SHAZAM_SUCCESS) {
                        mediaPlayer.setLooping(true);
                    } else {
                        mediaPlayer.setLooping(false);
                    }
                    int duration = mediaPlayer.getDuration();
                    int position = mSeekPosition * duration / PROGRESS_MAX;
                    LogUtil.d("播放总进度为：" + duration + " 要拖动到的播放位置为：" + position);
                    mediaPlayer.seekTo(position);*/
                mediaPlayer.seekTo(0);
            }
        });

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, "mediaPlayer onSeekComplete");
                startPlay();
                //releaseStopWakeLock();
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.d(TAG, "mediaPlayer onBufferingUpdate = " + percent);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放完毕，自动切歌
                Log.i(TAG, "mediaPlayer onCompletion auto to next song");
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "mediaPlayer onError what = " + what + ",extra = " + extra);
                //ToastUtil.showToast(mContext, "play error");
                //refreshNotification();
                return true; //返回值为true表示该方法处理该错误，否则会回调OnCompletionListener的onCompletion()方法
            }
        });

        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            //stopWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "netease-stop");
        } else {
            Log.e(TAG, "PowerManager is null");
        }
    }


    private void startPlay() {
        Log.d(TAG, "startPlay is ready");
        mediaPlayer.start();
        //EventBus.getDefault().post(new QingtingFmEvent(IQingtingFmEventType.EVENT_PLAY_FM_PLAY))
    }


    public void testSeek(View view) {
        Log.i(TAG,"testSeek");
        mediaPlayer.seekTo(33052);
    }

    public void testProgress(View view) {
        Log.i(TAG,"testProgress");
        int currentPosition = mediaPlayer.getCurrentPosition();
        Log.i(TAG,"currentPosition = " +currentPosition);
    }
}

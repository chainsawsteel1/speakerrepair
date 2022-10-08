package jp.chainsawsteel1.speaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int sound1, sound2, sound3;
    private Button button1, button2, button3, button4;
    private ImageView imageView1;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imageView1 = findViewById(R.id.imageView1);

        button3.setOnClickListener( v -> {
            audioPlay2();
            audioPlay2();
            Toast.makeText(getApplication(), "音を切り替えるには一度停止してください", Toast.LENGTH_SHORT).show();
        });

        button4.setOnClickListener( v -> {
            audioPlay3();
            audioPlay3();
            Toast.makeText(getApplication(), "音を切り替えるには一度停止してください", Toast.LENGTH_SHORT).show();
        });

        button2.setOnClickListener( v -> {
            RotateAnimation imgRotate = new RotateAnimation(
                    0, 360, (float) (imageView1.getWidth()/2), (float) (imageView1.getHeight()/2));
            imgRotate.setDuration(300);
            imageView1.startAnimation(imgRotate);
            if (mediaPlayer != null) {
                audioStop();
            }
            }
        );

    }

    private boolean audioSetup2(){
        boolean fileCheck = false;
        mediaPlayer = MediaPlayer.create(this, R.raw.two);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        fileCheck = true;
        return fileCheck;
    }

    private boolean audioSetup3(){
        boolean fileCheck = false;
        mediaPlayer = MediaPlayer.create(this, R.raw.three);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        fileCheck = true;
        return fileCheck;
    }

    private void audioPlay2() {
        if (mediaPlayer == null) {
            if (audioSetup2()){
                Log.d("debug","play audio");
            }
            else {
                Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener( mp -> {
                Log.d("debug","end of audio");
                audioStop1();
            });
        }
    }

    private void audioPlay3() {
        if (mediaPlayer == null) {
            if (audioSetup3()){
                Log.d("debug","play audio");
            }
            else {
                Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener( mp -> {
                Log.d("debug","end of audio");
                audioStop2();
            });
        }
    }

    private void audioStop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        Toast.makeText(getApplication(), "停止しました", Toast.LENGTH_SHORT).show();
    }

    private void audioStop1() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        Toast.makeText(getApplication(), "停止しました", Toast.LENGTH_SHORT).show();
    }

    private void audioStop2() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        Toast.makeText(getApplication(), "停止しました", Toast.LENGTH_SHORT).show();
    }
}
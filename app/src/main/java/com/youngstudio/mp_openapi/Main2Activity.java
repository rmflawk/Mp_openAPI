package com.youngstudio.mp_openapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    MainActivity main;

    public Main2Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv= findViewById(R.id.tv);
        iv= findViewById(R.id.iv);

        Animation ani= AnimationUtils.loadAnimation(this, R.anim.appear_logo);
        iv.startAnimation(ani);

        //애니메이션 리스너
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //findViewById(R.id.btn).setVisibility(View.VISIBLE);
                //startActivity(new Intent(SecondActivity.this, MainActivity.class));
//                main= new MainActivity();
//                main.plus();
                Intent intent= new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        tv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.appear_title));

        //Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();

    }
}

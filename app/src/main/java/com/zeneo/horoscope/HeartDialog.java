package com.zeneo.horoscope;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class HeartDialog extends Dialog {

    int index1,index2;


    public HeartDialog(Context context ,int index1 ,int index2 ) {
        super(context);
        this.index1 = index1;
        this.index2 = index2;
    }

    LottieAnimationView animationView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_heart);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        animationView = findViewById(R.id.animation_view);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(getContext(),LoveArtActivity.class);
                intent.putExtra("index1",index1);
                intent.putExtra("index2",index2);
                getContext().startActivity(intent);
                dismiss();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
}

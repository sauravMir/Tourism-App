package com.educareapps.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.educareapps.mylibrary.BaseActivity;

public class TourDetailActivity extends BaseActivity {
    ImageButton ibtnDetailBack;
    TourDetailActivity activity;
    int tourPic;
    String tourTitle, duration, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        activity = this;
        ibtnDetailBack = (ImageButton) findViewById(R.id.ibtnDetailBack);

        //tourPic = getIntent().getIntExtra(tourPic,0);


        ibtnDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

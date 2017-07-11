package com.educareapps.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.educareapps.mylibrary.BaseActivity;

public class TourDetailActivity extends BaseActivity {
    ImageButton ibtnDetailBack;
    TourDetailActivity activity;
    int tourPic;
    ImageView ivtourDetailPic;
    TextView tvDetailTitle, tvDetailDuration, tvDetailExplain;
    RatingBar rtBarDetail;
    String tourTitle, duration, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        activity = this;
        ibtnDetailBack = (ImageButton) findViewById(R.id.ibtnDetailBack);
        ivtourDetailPic = (ImageView) findViewById(R.id.ivtourDetailPic);
        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvDetailDuration = (TextView) findViewById(R.id.tvDetailDuration);
        tvDetailExplain = (TextView) findViewById(R.id.tvDetailExplain);
        rtBarDetail = (RatingBar) findViewById(R.id.rtBarDetail);


        /*tourPic = getIntent().getIntExtra("tourPic",0);
        tourTitle = getIntent().getStringExtra("tourTitle");
        duration = getIntent().getStringExtra("duration");
        detail = getIntent().getStringExtra("detail");*/

        /*ivtourDetailPic.setImageResource(tourPic);
        tvDetailTitle.setText(tourTitle);
        tvDetailDuration.setText(duration);
        tvDetailExplain.setText(detail);*/


        ibtnDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

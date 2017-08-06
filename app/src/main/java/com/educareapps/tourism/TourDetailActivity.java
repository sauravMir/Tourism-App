package com.educareapps.tourism;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.educareapps.model.TourismPlaceModel;
import com.educareapps.utilities.StaticInstance;

import java.util.HashMap;
import java.util.List;

public class TourDetailActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    ImageButton ibtnDetailBack, ibtnCall, ibtnMail;
    TourDetailActivity activity;
    int tourPic;

    TextView tvDetailTitle, tvDetailDuration, tvDetailExplain;
    RatingBar rtBarDetail;
    String tourTitle, duration, detail;
    SliderLayout mDemoSlider;
    StaticInstance staticInstance;
    TourismPlaceModel tourismPlaceModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        activity = this;
        ibtnDetailBack = (ImageButton) findViewById(R.id.ibtnDetailBack);
        ibtnCall = (ImageButton) findViewById(R.id.ibtnCall);
        ibtnMail = (ImageButton) findViewById(R.id.ibtnMail);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvDetailDuration = (TextView) findViewById(R.id.tvDetailDuration);
        tvDetailExplain = (TextView) findViewById(R.id.tvDetailExplain);
        rtBarDetail = (RatingBar) findViewById(R.id.rtBarDetail);

        staticInstance=StaticInstance.getInstance();
        tourismPlaceModel=staticInstance.getTourismPlaceModel();



        tvDetailTitle.setText(tourTitle);
        tvDetailDuration.setText(duration);
        tvDetailExplain.setText(detail);


        ibtnDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ibtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = getString(R.string.calling_numkber);
                Uri call = Uri.parse(getString(R.string.tel) + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
                finish();
            }
        });

        ibtnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                //finish();
            }
        });



        for(int i=0;i<tourismPlaceModel.getImageArr().size();i++){

            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView

                    .image(tourismPlaceModel.getImageArr().get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);



            mDemoSlider.addSlider(textSliderView);

        }


        tourTitle = tourismPlaceModel.getPackageName();
        duration =tourismPlaceModel.getPricePerPerson() ;
        detail = tourismPlaceModel.getCountry();



        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        rtBarDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(activity, String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        fullScreencall();
        UiChangeListener();

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }



    private void sendMail() {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        PackageManager pm = getPackageManager();
        Intent tempIntent = new Intent(Intent.ACTION_SEND);
        tempIntent.setType("*/*");
        List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
        for (int i = 0; i < resInfo.size(); i++) {
            ResolveInfo ri = resInfo.get(i);
            if (ri.activityInfo.packageName.contains("android.gm")) {
                myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                myIntent.setAction(Intent.ACTION_SEND);
                myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"edulifeapps01@gmail.com "});
                myIntent.setType("message/rfc822");
                myIntent.putExtra(Intent.EXTRA_SUBJECT, "Tour Info");
                myIntent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("Tour Info"));
            }
        }
        startActivity(myIntent);

    }

    // Full Screen display
    public void fullScreencall() {
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    // Navigation bar control
    public void UiChangeListener() {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });
    }


    // Device back pressed
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreencall();
        UiChangeListener();
    }






}

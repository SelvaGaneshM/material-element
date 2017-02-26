package com.github.takahirom.materialelement.creative;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.github.takahirom.materialelement.R;
import com.github.takahirom.materialelement.ScreenUtil;
import com.github.takahirom.materialelement.main.ImplementationItem;

public class CreativeCustomizationActivity extends AppCompatActivity {

    public final static String RESULT_EXTRA_ITEM_ID = "RESULT_EXTRA_ITEM_ID";
    public static final String INTENT_EXTRA_ITEM = "item";
    private ImplementationItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_customization);
        item = getIntent().getParcelableExtra(INTENT_EXTRA_ITEM);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();
    }

    public void setupViews() {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        final ImageView imageView = (ImageView) findViewById(R.id.detail_image);
//        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(DurationAndEasingActivity.this, android.R.color.white));
        imageView.setImageResource(item.imageRes);
        // If you want to load async, you can use this code
        //        ActivityCompat.postponeEnterTransition(this);
//        Glide.with(this).load(item.imageUrl).into(new GlideDrawableImageViewTarget(imageView) {
//            @Override
//            protected void setResource(GlideDrawable resource) {
//                super.setResource(resource);
//
//                imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        ActivityCompat.startPostponedEnterTransition(DurationAndEasingActivity.this);
//                        imageView.getViewTreeObserver().removeOnPreDrawListener(this);
//                        window_return true;
//                    }
//                });
//            }
//        });
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                collapsingToolbarLayout.setTitleEnabled(true);
                collapsingToolbarLayout.setTitle(item.title);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        final ImageView systemIconImageView = (ImageView) findViewById(R.id.system_icon);
        systemIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Animatable) systemIconImageView.getDrawable()).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResultAndFinish();
    }


    void setResultAndFinish() {
        final Intent resultData = new Intent();
        resultData.putExtra(RESULT_EXTRA_ITEM_ID, item.itemId);
        setResult(RESULT_OK, resultData);
        ActivityCompat.finishAfterTransition(this);
    }

}

package com.example.cars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

/**
 * Created by nulrybekkarshyga on 09.05.18.
 */

public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.first_slide_background)
                        .buttonsColor(R.color.first_slide_buttons)
                        .image(R.drawable.slide9)
                        .title("Find the best car with us")
                        .description("")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We provide solutions to make you love your decision");
                        Intent intentA = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intentA);
                    }
                }, "Would you try?"));

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.second_slide_background)
                .buttonsColor(R.color.second_slide_buttons)
                .image(R.drawable.slide12)
                .title("Check the map")
                .description("")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We provide solutions to make you love your decision");
                        Intent intentA = new Intent(IntroActivity.this, MapsActivity.class);
                        startActivity(intentA);
                    }
                }, "Go on"));

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.blue5)
                .buttonsColor(R.color.fourth_slide_buttons)
                .title("Send messages and interact with people around you")
                .image(R.drawable.slide7)
                .description("")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We provide solutions to make you love your decision");
                        Intent intentA = new Intent(IntroActivity.this, ChatActivity.class);
                        startActivity(intentA);
                    }
                }, "Would you join us?"));

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.slide8)
                .title("Mange your account")
                .description("And be in touch")
                .build());
        }
    }


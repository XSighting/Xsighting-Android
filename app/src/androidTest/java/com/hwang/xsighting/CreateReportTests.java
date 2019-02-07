package com.hwang.xsighting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.test.runner.intent.IntentCallback;
import android.support.test.runner.intent.IntentMonitorRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.OutputStream;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static android.app.Activity.RESULT_OK;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateReportTests {

    @Rule
    public ActivityTestRule<CreateSighting> createSightingActivity = new ActivityTestRule<>(CreateSighting.class);

    // UI Tests
    @Test
    public void submitReportButtonIsEnabled() {
        onView(withId(R.id.button_submit_report)).check(matches(isEnabled()));
    }

    @Test
    public void submitReportButtonIsDisplayed() {
        onView(withId(R.id.button_submit_report)).check(matches(isDisplayed()));
    }

    @Test
    public void submitReportButtonIsCompletelyDisplayed() {
        onView(withId(R.id.button_submit_report)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void submitReportButtonIsNotSelectable() {
        onView(withId(R.id.button_submit_report)).check(matches(not(isSelected())));
    }

    @Test
    public void submitReportButtonIsClickable() {
        onView(withId(R.id.button_submit_report)).check(matches(isClickable()));
    }

    @Test
    public void submitReportButtonWithText() {
        onView(withId(R.id.button_submit_report)).check(matches(withText(R.string.report_submit)));
    }

    @Test
    public void headerReportSightingWithText() {
        onView(withId(R.id.header_report_sighting)).check(matches(withText(R.string.header_report_sighting)));
    }

    @Test
    public void reportDescriptionLabelWithText() {
        onView(withId(R.id.report_description_label)).check(matches(withText(R.string.report_description_long)));
    }

    @Test
    public void editTextHint(){
        onView(withId(R.id.report_description)).check(matches(withHint("@string/report_description")));


    }




}

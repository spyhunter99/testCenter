
/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alexoree.testcenter.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

/**
 * borrowed from leak canary
 */
@TargetApi(ICE_CREAM_SANDWICH)
public final class ActivityWatcher {

    final static String TAG = "ActivityWatcher";
    static Activity topMostActivity=null;

    public static Activity getTopMostActivity(){
        return topMostActivity;
    }

    public static void installOnIcsPlus(Application application) {
        if (SDK_INT < ICE_CREAM_SANDWICH) {
            // If you need to support Android < ICS, override onDestroy() in your base activity.
            return;
        }
        ActivityWatcher activityRefWatcher = new ActivityWatcher(application);
        activityRefWatcher.watchActivities();
    }

    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks =
            new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                }

                @Override
                public void onActivityStarted(Activity activity) {
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    topMostActivity=activity;
                    Log.i(TAG, "Top level activity is now " + activity.getClass().getCanonicalName());
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    if (topMostActivity==activity){
                        topMostActivity=null;
                        Log.i(TAG, "Top level activity is now unknown");
                    }
                }

                @Override
                public void onActivityStopped(Activity activity) {
                    if (topMostActivity==activity){
                        topMostActivity=null;
                        Log.i(TAG, "Top level activity is now unknown");
                    }
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    ActivityWatcher.this.onActivityDestroyed(activity);

                }
            };

    private final Application application;

    /**
     * Constructs an {@link ActivityWatcher} that will make sure the activities are not leaking
     * after they have been destroyed.
     */
    public ActivityWatcher(Application application) {
        this.application = application;
    }

    void onActivityDestroyed(Activity activity) {
        if (topMostActivity==activity){
            topMostActivity=null;
            Log.i(TAG, "Top level activity is now unknown");
        }

    }

    public void watchActivities() {
        // Make sure you don't get installed twice.
        stopWatchingActivities();
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public void stopWatchingActivities() {
        application.unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }
}

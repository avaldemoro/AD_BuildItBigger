package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NonEmptyStringTest {
    private static final String LOG_TAG = "NON_EMPTY_STRING_TEST";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext ();

        String result = null;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask (appContext);
        endpointsAsyncTask.execute ();
        try {
            result = endpointsAsyncTask.get ();
            Log.d (LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        assertNotNull (result);
    }
}
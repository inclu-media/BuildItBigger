package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Martin Melcher on 03/03/16.
 */

@RunWith(AndroidJUnit4.class)
public class JokerBackendTest extends ActivityInstrumentationTestCase2 {

    private Context mContext;
    private CountDownLatch mSignal;

    public JokerBackendTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();

        // Injecting the Instrumentation instance is required
        // for your test to run with AndroidJUnitRunner.
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mContext = getActivity().getApplicationContext();

        mSignal = new CountDownLatch(1);
    }


    @Test
    public void testJokeIsFunny() throws InterruptedException {
        new EndpointAsyncTask() {

            @Override
            protected void onPostExecute(String joke) {

                // check that a joke is returned ("failed to connect" isn't one)
                assertTrue(joke != null && joke.length() > 0 && !joke.startsWith("failed"));

                mSignal.countDown();
                super.onPostExecute(joke);
            }
        }.execute(mContext);
        mSignal.await();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        if (mSignal != null) {
            mSignal.countDown();
        }
    }
}

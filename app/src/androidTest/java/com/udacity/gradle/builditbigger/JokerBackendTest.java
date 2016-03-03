package com.udacity.gradle.builditbigger;


import android.test.AndroidTestCase;

/**
 * Created by Martin Melcher on 03/03/16.
 */
public class JokerBackendTest extends AndroidTestCase {

    public void testJokeIsFunny() {
        new EndpointAsyncTask() {

            @Override
            protected void onPostExecute(String joke) {
                assertTrue(joke != null && joke.length() > 0);
                super.onPostExecute(joke);
            }
        }.execute(getContext());
    }
}

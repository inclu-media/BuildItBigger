package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import at.inclumedia.jokeractivity.JokerActivity;
import at.inclumedia.jokeractivity.JokerActivityFragment;

public class MainActivity extends AppCompatActivity {

    public interface SpinnerHolder {
        void startSpinner();
        void stopSpinner();
    }

    private InterstitialAd mInterstitialAd;
    public SpinnerHolder mSpinnerHolder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

                // start the spinner
                if (mSpinnerHolder != null) {
                    mSpinnerHolder.startSpinner();
                }

                // load joke and display
                new EndpointAsyncTask(){

                    @Override
                    protected void onPostExecute(String joke) {
                        // stop the spinner
                        if (mSpinnerHolder != null) {
                            mSpinnerHolder.stopSpinner();
                        }

                        Intent intent = new Intent(MainActivity.this, JokerActivity.class);
                        intent.putExtra(JokerActivityFragment.THE_JOKE, joke);
                        startActivity(intent);
                    }
                }.execute(MainActivity.this);

            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void startSpinner() {

    }

    public void stopSpinner() {

    }
}

package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import at.inclumedia.jokeractivity.JokerActivity;
import at.inclumedia.jokeractivity.JokerActivityFragment;

public class MainActivity extends AppCompatActivity {

    public interface SpinnerHolder {
        void startSpinner();
        void stopSpinner();
    }

    public SpinnerHolder mSpinnerHolder = null; ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        // start the spinner
        if (mSpinnerHolder != null) {
            mSpinnerHolder.startSpinner();
        }

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

                super.onPostExecute(joke);
            }
        }.execute(MainActivity.this);
    }
}

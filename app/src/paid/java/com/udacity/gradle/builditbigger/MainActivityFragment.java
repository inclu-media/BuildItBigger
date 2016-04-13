package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainActivity.SpinnerHolder {

    private ProgressBar mPgBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mPgBar = (ProgressBar)root.findViewById(R.id.pbBar);
        ((MainActivity)getActivity()).mSpinnerHolder = this;
        stopSpinner();

        return root;
    }

    @Override
    public void startSpinner() {
        mPgBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopSpinner() {
        mPgBar.setVisibility(View.GONE);
    }
}

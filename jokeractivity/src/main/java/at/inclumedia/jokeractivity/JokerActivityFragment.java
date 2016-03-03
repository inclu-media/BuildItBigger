package at.inclumedia.jokeractivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokerActivityFragment extends Fragment {

    public static final String THE_JOKE = "joke";

    public JokerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_joker, container, false);

        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(THE_JOKE);
        TextView tv = (TextView)rootView.findViewById(R.id.textView);
        tv.setText(joke);

        return rootView;
    }
}

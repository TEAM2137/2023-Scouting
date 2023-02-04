package com.team2137.rapidreactscouting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CommentsFragment extends ScoutFragment {
    private static final String TAG = "CommentsFragment";

    private EditText editTextComments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_comments, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get buttons from R and assign them locally to a button class of their name's.
        editTextComments = (EditText) view.findViewById(R.id.editTextComments);

        editTextComments.setText(MainActivity.instance.get().currentMatch.matchComments);

        editTextComments.addTextChangedListener(new TextWatcher() {
            private CharSequence value;
            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.instance.get().currentMatch.matchComments = value.toString();
                //Log.i(TAG, "COMMENT:"+value);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value = charSequence;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
    }

    @Override
    public void reloadDisplays() {
        editTextComments.setText(MainActivity.instance.get().currentMatch.matchComments);
    }
}
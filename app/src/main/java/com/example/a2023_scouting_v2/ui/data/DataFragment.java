package com.example.a2023_scouting_v2.ui.data;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.R;
import com.example.a2023_scouting_v2.databinding.FragmentDataBinding;

import java.io.File;

public class DataFragment extends Fragment {
    private FragmentDataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataViewModel notificationsViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDataTop;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button deleteMatches = binding.deleteMatchesData;

        deleteMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(root.getContext())
                        .setTitle("Are you sure?")
                        .setMessage("This will delete all saved match data on this tablet.")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv")));
                                if (file.delete()) {
                                    Toast.makeText(getActivity(), "Match data successfully deleted.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getActivity(), "Match data failed to delete. Was it already deleted?",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                Toast.makeText(getActivity(), "Match data will remember that",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(R.drawable.warning_icon)
                        .show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
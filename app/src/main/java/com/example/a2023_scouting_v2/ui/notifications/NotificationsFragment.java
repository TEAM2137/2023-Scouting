package com.example.a2023_scouting_v2.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.databinding.FragmentEndgameBinding;
import com.example.a2023_scouting_v2.ui.dashboard.DashboardFragment;
import com.example.a2023_scouting_v2.ui.home.HomeFragment;

import java.io.File;
import java.io.FileWriter;

public class NotificationsFragment extends Fragment {

    private FragmentEndgameBinding binding;
    public String finalendvalue = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentEndgameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RadioButton engagedB = binding.engagedTE;
        RadioButton dockedB = binding.dockedTE;
        RadioButton parkedB = binding.parkedTE;
        RadioButton noneB = binding.noneTE;
        Button submitInfo = binding.saveData;

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "matches.csv";
        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath);
        CSVWriter writer;

        // File exist
        if(f.exists()&&!f.isDirectory())
        {
            mFileWriter = new FileWriter(filePath, true);
            writer = new CSVWriter(mFileWriter);
        }
        else
        {
            writer = new CSVWriter(new FileWriter(filePath));
        }

        String[] data = {"Ship Name", "Scientist Name", "...", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").formatter.format(date)});

        writer.writeNext(data);

        writer.close();

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment auto = new HomeFragment();

                if(engagedB.isChecked()){
                    finalendvalue = "Engaged";
                    System.out.println("Saved as 'Engaged'." + auto.topCount);
                } else if (dockedB.isChecked()) {
                    finalendvalue = "Docked";
                    System.out.println("Saved as 'Docked'.");
                } else if (parkedB.isChecked()) {
                    finalendvalue = "Parked";
                    System.out.println("Saved as 'Parked'.");
                } else if (noneB.isChecked()) {
                    finalendvalue = "None";
                    System.out.println("Saved as 'None'.");
                }
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
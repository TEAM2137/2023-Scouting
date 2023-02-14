package com.example.a2023_scouting_v2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.databinding.FragmentAutoBinding;

public class HomeFragment extends Fragment {

    private FragmentAutoBinding binding;

    int topCount = 0;
    int midCount = 0;
    int lowCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentAutoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAutoTop;
        homeViewModel.getTitle().observe(getViewLifecycleOwner(), textView::setText);

        final TextView topScoreNum = binding.topScoreTA;
        final TextView midScoreNum = binding.midScoreTA;
        final TextView lowScoreNum = binding.lowScoreTA;

        //Top + & - buttons
        Button topPlusA = binding.topPlusA;
        Button topMinusA = binding.topMinusA;

        //Medium + & - buttons
        Button midPlusA = binding.midPlusA;
        Button midMinusA = binding.midMinusA;

        //Low + & - buttons
        Button lowPlusA = binding.lowPlusA;
        Button lowMinusA = binding.lowMinusA;


        // Top score button functionality
        topPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topCount > 8){
                    topCount = 0;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                } else {
                    topCount++;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                }
            }
        });

        topMinusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topCount < 1){
                    topCount = 9;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                } else {
                    topCount--;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                }
            }
        });

        // Mid score button functionality
        midPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(midCount > 8){
                    midCount = 0;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                } else {
                    midCount++;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                }
            }
        });

        midMinusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(midCount < 1){
                    midCount = 9;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                } else {
                    midCount--;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                }
            }
        });

        // Low score button functionality
        lowPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowCount > 8){
                    lowCount = 0;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                } else {
                    lowCount++;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                }
            }
        });

        lowMinusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowCount < 1){
                    lowCount = 9;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                } else {
                    lowCount--;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
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
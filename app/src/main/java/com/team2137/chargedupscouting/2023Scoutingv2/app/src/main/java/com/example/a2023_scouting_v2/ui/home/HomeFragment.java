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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentAutoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAutoTop;
        homeViewModel.getTitle().observe(getViewLifecycleOwner(), textView::setText);

        final TextView topScoreNum = binding.topScoreT;

        Button topPlusA = binding.topPlus;
        Button topMinusA = binding.topMinus;

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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.week09;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DynamicFragment extends Fragment {

    private int type = 0;
    private String message = "안녕하세요";

    public static DynamicFragment newInstance(int type, String message) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            type = getArguments().getInt("type", 0);
            message = getArguments().getString("message", "안녕하세요");
        }
        View view;
        if (type == 0) {
            view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_a, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
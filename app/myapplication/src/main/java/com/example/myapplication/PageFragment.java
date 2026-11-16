package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private static final String ARG_TEXT = "text";
    private static final String ARG_BG_COLOR = "bg_color";
    private static final String ARG_IS_LIST = "is_list";

    public static PageFragment newInstance(String text, int bgColor, boolean isList) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putBoolean(ARG_IS_LIST, isList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        LinearLayout pageLayout = view.findViewById(R.id.pageLayout);
        TextView tvPage = view.findViewById(R.id.tvPage);
        ListView listView = view.findViewById(R.id.listView);

        if (getArguments() != null) {
            String text = getArguments().getString(ARG_TEXT);
            int bgColor = getArguments().getInt(ARG_BG_COLOR);
            boolean isList = getArguments().getBoolean(ARG_IS_LIST);

            pageLayout.setBackgroundColor(bgColor);

            if (isList) {
                // SETTING 탭 클릭 시 리스트뷰 표시
                tvPage.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                String[] names = {"홍길동", "이대한", "한민국", "이윤지"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        names
                );
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view1, position, id) -> {
                    Toast.makeText(requireContext(), names[position], Toast.LENGTH_SHORT).show();
                });
            } else {
                tvPage.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                tvPage.setText(text);
            }
        }

        return view;
    }
}